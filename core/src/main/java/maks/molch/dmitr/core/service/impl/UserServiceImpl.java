package maks.molch.dmitr.core.service.impl;

import lombok.AllArgsConstructor;
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

    @Override
    public UserTableRecord register(UserTableRecord user) throws AlreadyExistException {
        try {
            return userRepo.save(user);
        } catch (IntegrityConstraintViolationException e) {
            throw new AlreadyExistException();
        }
    }
}
