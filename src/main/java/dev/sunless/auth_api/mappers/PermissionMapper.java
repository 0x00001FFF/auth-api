package dev.sunless.auth_api.mappers;

import dev.sunless.auth_api.dtos.request.PermissionRequestDto;
import dev.sunless.auth_api.dtos.response.InactivePermissionResponseDto;
import dev.sunless.auth_api.dtos.response.PermissionResponseDto;
import dev.sunless.auth_api.models.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class PermissionMapper {

    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModified", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Permission toModel(PermissionRequestDto dto);

    @Mapping(target = "id", source = "permission.id")
    @Mapping(target = "createdDate", source = "permission.createdDate")
    @Mapping(target = "lastModified", source = "permission.lastModified")
    public abstract PermissionResponseDto toResponse(Permission permission);

    @Mapping(target = "id", source = "permission.id")
    @Mapping(target = "name", source = "permission.name")
    @Mapping(target = "deletedAt", source = "permission.deletedAt")
    @Mapping(target = "createdDate", source = "permission.createdDate")
    @Mapping(target = "lastModified", source = "permission.lastModified")
    public abstract InactivePermissionResponseDto toInactiveResponse(Permission permission);
}
