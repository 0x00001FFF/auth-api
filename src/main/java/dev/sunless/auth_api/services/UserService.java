package dev.sunless.auth_api.services;

import dev.sunless.auth_api.models.User;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserService {

    @Transactional
    User save(User user, Set<UUID> roles);

    @Transactional
    User update(User newUser, UUID id);

    @Transactional
    User updateRoles(UUID id, Set<UUID> roles);

    Optional<User> findById(UUID id);

    Optional<User> findByIdActive(UUID id);

    List<User> findAll();

    List<User> findAllInactive();

    void deleteById(UUID id);

    User softDeleteById(UUID id);

    User undeleteById(UUID id);

}
