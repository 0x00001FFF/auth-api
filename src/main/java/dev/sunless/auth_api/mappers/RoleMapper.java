package dev.sunless.auth_api.mappers;

import dev.sunless.auth_api.dtos.request.RoleRequestDto;
import dev.sunless.auth_api.dtos.response.InactiveRoleResponseDto;
import dev.sunless.auth_api.dtos.response.RoleResponseDto;
import dev.sunless.auth_api.models.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponseDto toResponse(RoleResponseDto responseDto);

    Role toModel(RoleRequestDto requestDto);

    @Mapping(target = "deletedAt", source = "permission.deletedAt")
    InactiveRoleResponseDto toInactiveResponse(Role role);
}
