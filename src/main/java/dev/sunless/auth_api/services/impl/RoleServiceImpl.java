package dev.sunless.auth_api.services.impl;

import dev.sunless.auth_api.exceptions.DuplicatedException;
import dev.sunless.auth_api.exceptions.NotFoundException;
import dev.sunless.auth_api.models.Permission;
import dev.sunless.auth_api.models.Role;
import dev.sunless.auth_api.repositories.RoleRepository;
import dev.sunless.auth_api.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        String name = role.getName();
        if (roleRepository.existsByName(name)) throw new DuplicatedException("Role");
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role newRole, UUID id) {
        Role role = roleExistsOrThrow(id);
        String name = role.getName();
        if (roleRepository.existsByName(name)) throw new DuplicatedException("Role");
        LocalDateTime createdDate = role.getCreatedDate();
        BeanUtils.copyProperties(newRole, role, "id", "createdDate");
        role.setCreatedDate(createdDate);
        return roleRepository.save(role);
    }

    @Override
    public Role updatePermissions(UUID id, Set<UUID> permissions) {
        Role role = roleExistsOrThrow(id);
        if (role.isSoftDeleted())
            throw new IllegalStateException("Cannot update the permissions of an inactive Role");
        List<Permission> newPermissions = permissionRepository.findByIdIn(permissions);
        if (newPermissions.size() != permissions.size())
            throw new IllegalArgumentException("Theres a invalid permission in the request");
        role.setPermissions(new HashSet<>(newPermissions));
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> findByIdActive(UUID id) {
        return roleRepository.findByIdActive(id);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAllActive();
    }

    @Override
    public List<Role> findAllInactive() {
        return roleRepository.findAllInactive();
    }

    @Override
    public void deleteById(UUID id) {
        Role role = roleExistsOrThrow(id);
        if(!role.isSoftDeleted())
            throw new IllegalStateException("Cannot hard delete an active Role");
        roleRepository.deleteById(id);
    }

    @Override
    public Role softDeleteById(UUID id) {
        Role role = roleExistsOrThrow(id);
        roleRepository.softDeleteById(id);
        return role;
    }

    @Override
    public Role undeleteById(UUID id) {
        Role role = roleExistsOrThrow(id);
        if(!role.isSoftDeleted())
            throw new IllegalStateException("Cannot undelete an active role");
        roleRepository.undeleteById(id);
        return role;
    }

    @Override
    public Boolean existsById(UUID id) {
        return roleRepository.existsById(id);
    }

    private Role roleExistsOrThrow(UUID id) {
        return findById(id).orElseThrow(
                () -> new NotFoundException("Role with ID: " + id)
        );
    }
}
