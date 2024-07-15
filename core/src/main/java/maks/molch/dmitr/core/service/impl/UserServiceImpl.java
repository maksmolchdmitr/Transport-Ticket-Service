package maks.molch.dmitr.core.service.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.mapper.UserMapper;
import maks.molch.dmitr.core.model.User;
import maks.molch.dmitr.core.repo.UserRepo;
import maks.molch.dmitr.core.service.UserService;
import maks.molch.dmitr.core.service.exception.AlreadyExistException;
import org.jooq.exception.IntegrityConstraintViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public User register(User user) throws AlreadyExistException {
        try {
            var record = userMapper.toRecord(user);
            var createdRecord = userRepo.save(record);
            return userMapper.toUser(createdRecord);
        } catch (IntegrityConstraintViolationException e) {
            throw new AlreadyExistException("User with such login already exist");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userRecord = userRepo.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        var user = userMapper.toUser(userRecord);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.login())
                .password(user.password())
                .roles(user.role().name())
                .build();
    }
}
