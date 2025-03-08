package dev.sunless.auth_api.services.impl;

import dev.sunless.auth_api.exceptions.DuplicatedException;
import dev.sunless.auth_api.exceptions.NotFoundException;
import dev.sunless.auth_api.models.Permission;
import dev.sunless.auth_api.repositories.PermissionRepository;
import dev.sunless.auth_api.services.PermissionService;
import dev.sunless.auth_api.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final RoleService roleService;

    @Override
    public Permission save(Permission permission) {
        String name = permission.getName();
        if(permissionRepository.existsByName(name)) throw new DuplicatedException(name);
        return permissionRepository.save(permission);
    }

    @Override
    public Permission update(Permission newPermission, UUID id) {
        Permission permission = findByIdActive(id).orElseThrow(() -> new NotFoundException("Permission with id: %s".formatted(id)));
        String name = newPermission.getName();
        if(permissionRepository.existsByName(name)) throw new DuplicatedException(name);
        LocalDateTime createdDate = permission.getCreatedDate();
        BeanUtils.copyProperties(newPermission, permission, "id", "createdDate");
        permission.setCreatedDate(createdDate);
        return permissionRepository.save(permission);
    }

    @Override
    public Optional<Permission> findByIdActive(UUID id) {
        return permissionRepository.findByIdActive(id);
    }

    @Override
    public Optional<Permission> findById(UUID id) {
        return permissionRepository.findById(id);
    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAllActive();
    }

    @Override
    public List<Permission> findAllInactive() {
        return permissionRepository.findAllInactive();
    }

    @Override
    public void deleteById(UUID id) {
        Permission permission = permissionExistsOrThrow(id);

        if (!permission.isSoftDeleted())
            throw new IllegalStateException("Cannot hard delete an active permission");
        permissionRepository.deleteById(id);
    }

    @Override
    public Permission softDeleteById(UUID id) {
        Permission permission = permissionExistsOrThrow(id);
        roleService.removePermissionFromRoles(id);
        permissionRepository.softDeleteById(id);
        return permission;
    }

    @Override
    public Permission undeleteById(UUID id) {
        Permission permission = permissionExistsOrThrow(id);
        if(!permission.isSoftDeleted())
            throw new IllegalStateException("Permission is already active, cannot undelete");
        permissionRepository.undeleteById(id);
        return permission;
    }

    @Override
    public Boolean existsById(UUID id) {
        return permissionRepository.existsById(id);
    }

    private Permission permissionExistsOrThrow(UUID id) {
        return findById(id).orElseThrow(
                () -> new NotFoundException("Permission with ID: " + id)
        );
    }

}
