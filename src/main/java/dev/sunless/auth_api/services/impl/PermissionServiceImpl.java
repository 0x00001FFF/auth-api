package dev.sunless.auth_api.services.impl;

import dev.sunless.auth_api.events.PermissionDeletedEvent;
import dev.sunless.auth_api.exceptions.DuplicatedException;
import dev.sunless.auth_api.exceptions.NotFoundException;
import dev.sunless.auth_api.models.Permission;
import dev.sunless.auth_api.repositories.PermissionRepository;
import dev.sunless.auth_api.services.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final ApplicationEventPublisher eventPublisher;

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
        eventPublisher.publishEvent(new PermissionDeletedEvent(this, id));
        permissionRepository.deleteById(id);
    }

    @Override
    public Permission softDeleteById(UUID id) {
        Permission permission = permissionExistsOrThrow(id);
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

    @Override
    public Set<Permission> findByIdIn(Set<UUID> ids) {
        return new HashSet<>(permissionRepository.findByIdIn(ids));
    }
}
