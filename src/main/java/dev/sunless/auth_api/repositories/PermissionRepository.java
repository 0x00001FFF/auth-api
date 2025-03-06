package dev.sunless.auth_api.repositories;

import dev.sunless.auth_api.models.Permission;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PermissionRepository extends BaseRepository<Permission, UUID>{

    Boolean existsByName(String name);

}
