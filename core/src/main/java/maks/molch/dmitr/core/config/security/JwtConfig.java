package maks.molch.dmitr.core.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class JwtConfig {
    @ConfigurationProperties(prefix = "application.jwt")
    public record JwtConfigProperties(
            String privateKey,
            String publicKey,
            Integer expireTimeAccessTokenInMinutes,
            Integer expireTimeRefreshTokenInDays
    ) {
    }

    @Bean
    public PrivateKey getPrivateKey(JwtConfigProperties config) throws InvalidKeySpecException, NoSuchAlgorithmException {
        var cleanKey = config.privateKey().replaceAll("\\s+", "");
        var keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(cleanKey));
        var keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    @Bean
    public PublicKey getPublicKey(JwtConfigProperties config) throws InvalidKeySpecException, NoSuchAlgorithmException {
        var cleanKey = config.publicKey().replaceAll("\\s+", "");
        var keyFactory = KeyFactory.getInstance("RSA");
        var keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(cleanKey));
        return keyFactory.generatePublic(keySpec);
    }
}
