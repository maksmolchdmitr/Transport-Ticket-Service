package maks.molch.dmitr.core.service;

import maks.molch.dmitr.core.jooq.tables.records.UserTableRecord;

public interface UserService {
    UserTableRecord register(UserTableRecord user);
}
