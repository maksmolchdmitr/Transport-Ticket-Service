package maks.molch.dmitr.core.service.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.config.security.AdminConfig;
import maks.molch.dmitr.core.mapper.UserMapper;
import maks.molch.dmitr.core.repo.UserRepo;
import maks.molch.dmitr.core.service.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class AdminInserterOnStart implements CommandLineRunner {
    private static final Logger logger = Logger.getLogger(AdminInserterOnStart.class.getName());

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AdminConfig adminConfig;

    @Override
    public void run(String... args) {
        try {
            var admin = new User(
                    adminConfig.login(),
                    adminConfig.password(),
                    "adminov admin adminovich",
                    User.Role.ADMIN
            );
            userRepo.save(userMapper.toRecord(admin, passwordEncoder));
        } catch (Throwable e) {
            logger.log(Level.WARNING, "Insert admin failed", e);
        }
    }
}
