package dev.sunless.auth_api.mappers;

import dev.sunless.auth_api.dtos.request.PermissionRequestDto;
import dev.sunless.auth_api.dtos.response.InactivePermissionResponseDto;
import dev.sunless.auth_api.dtos.response.PermissionResponseDto;
import dev.sunless.auth_api.models.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PermissionMapper {

    @Mapping(target = "name", source = "name")
    public abstract Permission toModel(PermissionRequestDto dto);

    @Mapping(target = "id", source = "permission.id")
    @Mapping(target = "createdDate", source = "createdDate")
    public abstract PermissionResponseDto toResponse(Permission permission);

    @Mapping(target = "id", source = "permission.id")
    @Mapping(target = "name", source = "permission.name")
    @Mapping(target = "deletedAt", source = "permission.deletedAt")
    public abstract InactivePermissionResponseDto toInactiveResponse(Permission permission);
}
