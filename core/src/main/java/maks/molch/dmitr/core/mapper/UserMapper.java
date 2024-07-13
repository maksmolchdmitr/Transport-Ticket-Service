package maks.molch.dmitr.core.mapper;

import maks.molch.dmitr.core.dto.UserCreateRequestDto;
import maks.molch.dmitr.core.dto.UserDto;
import maks.molch.dmitr.core.jooq.tables.records.UserTableRecord;
import maks.molch.dmitr.core.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserTableRecord toRecord(User user);

    User toUser(UserTableRecord createdRecord);

    User toUser(UserDto user);

    UserDto toDto(User userRecord);

    User toUser(UserCreateRequestDto createRequestDto);
}
