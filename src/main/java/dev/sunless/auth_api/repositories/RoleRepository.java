package dev.sunless.auth_api.repositories;

import dev.sunless.auth_api.models.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface RoleRepository extends BaseRepository<Role, UUID>{

    Boolean existsByName(String name);

    @Query("SELECT r FROM Role r JOIN r.permissions p WHERE p.id = :id")
    List<Role> findByPermissionId(UUID id);

    @Query("SELECT r FROM Role r WHERE r.id IN :ids AND r.deletedAt IS NULL")
    List<Role> findByIdIn(@Param("ids") Set<UUID> ids);
}
