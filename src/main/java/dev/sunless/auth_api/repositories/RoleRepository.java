package dev.sunless.auth_api.repositories;

import dev.sunless.auth_api.models.Permission;
import dev.sunless.auth_api.models.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends BaseRepository<Role, UUID>{

    Boolean existsByName(String name);

    @Query("SELECT * FROM Permission p WHERE p.id IN :ids AND p.deletedAt IS NULL")
    List<Permission> findByIdIn(List<UUID> id);
}
