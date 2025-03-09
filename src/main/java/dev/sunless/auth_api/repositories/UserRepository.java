package dev.sunless.auth_api.repositories;

import dev.sunless.auth_api.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends BaseRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Boolean existsByUserName(String name);
}
