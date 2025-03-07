package dev.sunless.auth_api.services.impl;

import dev.sunless.auth_api.exceptions.DuplicatedException;
import dev.sunless.auth_api.exceptions.NotFoundException;
import dev.sunless.auth_api.models.Role;
import dev.sunless.auth_api.repositories.RoleRepository;
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
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        String name = role.getName();
        if (!roleRepository.existsByName(name)) throw new DuplicatedException("Role");
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role newRole, UUID id) {
        return null;
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<Role> findByIdActive(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<Role> findAll() {
        return List.of();
    }

    @Override
    public List<Role> findAllInactive() {
        return List.of();
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public Role softDeleteById(UUID id) {
        return null;
    }

    @Override
    public Role undeleteById(UUID id) {
        return null;
    }

    @Override
    public Boolean existsById(UUID id) {
        return null;
    }
}
