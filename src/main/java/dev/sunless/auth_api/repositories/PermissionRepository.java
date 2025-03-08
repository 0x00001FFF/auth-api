package dev.sunless.auth_api.repositories;

import dev.sunless.auth_api.models.Permission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface PermissionRepository extends BaseRepository<Permission, UUID>{

    Boolean existsByName(String name);

    @Query("SELECT p FROM Permission p WHERE p.id IN :ids AND p.deletedAt IS NULL")
    List<Permission> findByIdIn(@Param("ids") Set<UUID> permissionIds);

}
