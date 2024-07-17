package maks.molch.dmitr.core.mapper;

import maks.molch.dmitr.core.dto.response.AuthenticationResponseDto;
import maks.molch.dmitr.core.jooq.tables.records.TokenTableRecord;
import maks.molch.dmitr.core.service.entity.AccessAndRefreshToken;
import maks.molch.dmitr.core.service.entity.Token;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenMapper {
    AuthenticationResponseDto toDto(AccessAndRefreshToken accessAndRefreshToken);

    AccessAndRefreshToken toToken(AuthenticationResponseDto authenticationResponseDto);

    TokenTableRecord toRecord(Token token);
}
