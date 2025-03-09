package dev.sunless.auth_api.repositories;

import dev.sunless.auth_api.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends BaseRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Boolean existsByUserName(String name);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.id = :id")
    List<User> findByRoleId(UUID id);
}
