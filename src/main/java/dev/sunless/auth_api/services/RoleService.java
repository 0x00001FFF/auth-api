package dev.sunless.auth_api.services;

import dev.sunless.auth_api.models.Role;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RoleService {


    @Transactional
    Role save(Role role, Set<UUID> permissions);

    @Transactional
    Role update(Role newRole, UUID id);

    @Transactional
    Role updatePermissions(UUID id, Set<UUID> permissions);

    @Transactional
    void removePermissionFromRoles(UUID id);

    Optional<Role> findById(UUID id);

    Optional<Role> findByIdActive(UUID id);

    List<Role> findAll();

    List<Role> findAllInactive();

    void deleteById(UUID id);

    Role softDeleteById(UUID id);

    Role undeleteById(UUID id);

    Boolean existsById(UUID id);

    Set<Role> findByIdIn(Set<UUID> ids);
}
