package dev.sunless.auth_api.services;

import dev.sunless.auth_api.models.Permission;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface PermissionService {

    @Transactional
    Permission save(Permission permission);

    @Transactional
    Permission update(Permission permission, UUID id);

    Optional<Permission> findById(UUID id);

    Optional<Permission> findByIdActive(UUID id);

    List<Permission> findAll();

    List<Permission> findAllInactive();

    void deleteById(UUID id);

    Permission softDeleteById(UUID id);

    Permission undeleteById(UUID id);

    Boolean existsById(UUID id);

    Set<Permission> findByIdIn(Set<UUID> ids);
}
