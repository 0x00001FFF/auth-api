package dev.sunless.auth_api.mappers;

import dev.sunless.auth_api.dtos.request.RoleRequestDto;
import dev.sunless.auth_api.dtos.response.InactiveRoleResponseDto;
import dev.sunless.auth_api.dtos.response.RoleResponseDto;
import dev.sunless.auth_api.models.Permission;
import dev.sunless.auth_api.models.Role;
import dev.sunless.auth_api.repositories.PermissionRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class RoleMapper {

    @Autowired
    private PermissionRepository permissionRepository;

    @Mapping(target = "permissions", source = "role.permissions")
    public abstract RoleResponseDto toResponse(Role role);

    @Mapping(target = "permissions", source = "permissions")
    public abstract Role toModel(RoleRequestDto requestDto, Set<Permission> permissions);

    @Mapping(target = "deletedAt", source = "deletedAt")
    @Mapping(target = "createdDate", source = "createdDate")
    @Mapping(target = "lastModified", source = "lastModified")
    public abstract InactiveRoleResponseDto toInactiveResponse(Role role);

}
