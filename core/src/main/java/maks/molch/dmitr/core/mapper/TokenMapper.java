package maks.molch.dmitr.core.mapper;

import maks.molch.dmitr.core.dto.response.AuthenticationResponseDto;
import maks.molch.dmitr.core.jooq.tables.records.TokenTableRecord;
import maks.molch.dmitr.core.service.auth.entity.AccessAndRefreshToken;
import maks.molch.dmitr.core.service.auth.entity.Token;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenMapper {
    AuthenticationResponseDto toDto(AccessAndRefreshToken accessAndRefreshToken);

    TokenTableRecord toRecord(Token token);
}
