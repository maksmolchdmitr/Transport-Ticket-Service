package maks.molch.dmitr.core.service.impl;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.mapper.UserMapper;
import maks.molch.dmitr.core.model.User;
import maks.molch.dmitr.core.repo.UserRepo;
import maks.molch.dmitr.core.service.UserService;
import maks.molch.dmitr.core.jooq.tables.records.UserTableRecord;
import maks.molch.dmitr.core.service.exception.AlreadyExistException;
import org.jooq.exception.IntegrityConstraintViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
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
}
