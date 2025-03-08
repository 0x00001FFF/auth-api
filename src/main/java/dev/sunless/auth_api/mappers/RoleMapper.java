package dev.sunless.auth_api.mappers;

import dev.sunless.auth_api.dtos.request.RoleRequestDto;
import dev.sunless.auth_api.dtos.response.InactiveRoleResponseDto;
import dev.sunless.auth_api.dtos.response.PermissionResponseDto;
import dev.sunless.auth_api.dtos.response.RoleResponseDto;
import dev.sunless.auth_api.models.Permission;
import dev.sunless.auth_api.models.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class RoleMapper {

    @Autowired
    private PermissionMapper permissionMapper;

    @Mapping(target = "permissions",
            expression = """
                    java(
                        filterActivePermissions(role.getPermissions())
                    )
                    """)
    @Mapping(target = "createdDate", source = "role.createdDate")
    @Mapping(target = "lastModified", source = "role.lastModified")
    @Mapping(target = "id", source = "role.id")
    public abstract RoleResponseDto toResponse(Role role);

    @Mapping(target = "permissions", source = "permissions")
    @Mapping(target = "name", source = "requestDto.name")
    @Mapping(target = "description", source = "requestDto.description")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModified", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Role toModel(RoleRequestDto requestDto, Set<Permission> permissions);

    @Mapping(target = "deletedAt", source = "role.deletedAt")
    @Mapping(target = "createdDate", source = "role.createdDate")
    @Mapping(target = "lastModified", source = "role.lastModified")
    @Mapping(target = "id", source = "role.id")
    @Mapping(target = "name", source = "role.name")
    public abstract InactiveRoleResponseDto toInactiveResponse(Role role);

    protected Set<PermissionResponseDto> filterActivePermissions(Set<Permission> permissions) {
        return permissions
                .stream()
                .filter(permission -> permission.getDeletedAt() == null)
                .map(permission -> permissionMapper.toResponse(permission))
                .collect(Collectors.toSet());
    }

}
