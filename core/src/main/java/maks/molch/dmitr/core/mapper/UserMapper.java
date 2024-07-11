package maks.molch.dmitr.core.mapper;

import maks.molch.dmitr.core.dto.UserDto;
import maks.molch.dmitr.core.jooq.tables.records.UserTableRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserTableRecord dtoToRecord(UserDto user);

    UserDto recordToDto(UserTableRecord userRecord);
}
