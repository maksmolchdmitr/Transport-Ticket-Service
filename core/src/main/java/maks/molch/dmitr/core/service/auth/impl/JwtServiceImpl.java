package maks.molch.dmitr.core.service.auth.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.config.security.JwtConfig;
import maks.molch.dmitr.core.service.auth.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final JwtConfig config;

    @Override
    public String generateAccessToken(Authentication authData) {
        return generateToken(authData, Date.from(LocalDateTime.now()
                .plusMinutes(config.expireTimeAccessTokenInMinutes())
                .atZone(ZoneId.systemDefault())
                .toInstant()));
    }

    @Override
    public String generateRefreshToken(Authentication authData) {
        return generateToken(authData, Date.from(LocalDateTime.now()
                .plusDays(config.expireTimeRefreshTokenInDays())
                .atZone(ZoneId.systemDefault())
                .toInstant()));
    }

    private String generateToken(Authentication authData, Date expirationDate) {
        return Jwts.builder()
                .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
                .claim("username", authData.getName())
                .claim("authorities", authData.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .setExpiration(expirationDate)
                .setSubject(authData.getName())
                .compact();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Authentication parseToken(String jwt) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(getPublicKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        List<String> roles = (List<String>) claims.get("authorities");
        var authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
    }

    private PrivateKey getPrivateKey() {
        try {
            var cleanKey = config.privateKey().replaceAll("\\s+", "");
            var keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(cleanKey));
            var keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            return null;
        }
    }

    private PublicKey getPublicKey() {
        try {
            var cleanKey = config.publicKey().replaceAll("\\s+", "");
            var keyFactory = KeyFactory.getInstance("RSA");
            var keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(cleanKey));
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            return null;
        }
    }
}
