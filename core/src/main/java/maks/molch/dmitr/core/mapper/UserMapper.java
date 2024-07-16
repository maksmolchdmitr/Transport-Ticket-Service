package maks.molch.dmitr.core.mapper;

import maks.molch.dmitr.core.dto.UserCreateRequestDto;
import maks.molch.dmitr.core.dto.UserDto;
import maks.molch.dmitr.core.jooq.tables.records.UserTableRecord;
import maks.molch.dmitr.core.service.entity.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "user.password", target = "password", qualifiedByName = "encodePassword")
    UserTableRecord toRecord(User user, @Context PasswordEncoder passwordEncoder);

    @Named("encodePassword")
    default String encodePassword(String password, @Context PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(password);
    }

    User toUser(UserTableRecord createdRecord);

    User toUser(UserDto user);

    UserDto toDto(User userRecord);

    User toUser(UserCreateRequestDto createRequestDto);
}
