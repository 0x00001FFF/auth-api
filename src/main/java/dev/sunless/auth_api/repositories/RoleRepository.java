package dev.sunless.auth_api.repositories;

import dev.sunless.auth_api.models.Role;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends BaseRepository<Role, UUID>{

    Boolean existsByName(String name);

}
