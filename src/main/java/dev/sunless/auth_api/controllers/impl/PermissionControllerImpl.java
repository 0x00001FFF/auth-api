package dev.sunless.auth_api.controllers.impl;

import dev.sunless.auth_api.controllers.PermissionController;
import dev.sunless.auth_api.dtos.request.PermissionRequestDto;
import dev.sunless.auth_api.dtos.response.InactivePermissionResponseDto;
import dev.sunless.auth_api.dtos.response.PermissionResponseDto;
import dev.sunless.auth_api.mappers.PermissionMapper;
import dev.sunless.auth_api.models.Permission;
import dev.sunless.auth_api.services.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping(PermissionControllerImpl.BASE_URL)
@RequiredArgsConstructor
public class PermissionControllerImpl implements PermissionController {

    public static final String BASE_URL = "permissions";
    private final PermissionService permissionService;
    @Autowired
    private final PermissionMapper permissionMapper;

    @Override
    public ResponseEntity<List<PermissionResponseDto>> findAllActive() {
        List<Permission> permissions = permissionService.findAll();
        return Optional.of(permissions)
            .filter(list -> !list.isEmpty())
            .map(list -> list
                    .stream()
                    .map(permissionMapper::toResponse)
                    .toList())
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.noContent().build());
    }

    @Override
    public ResponseEntity<List<InactivePermissionResponseDto>> findAllInactive() {
        List<Permission> permissions = permissionService.findAllInactive();
        return Optional.of(permissions)
                .filter(list -> !list.isEmpty())
                .map(list -> list
                        .stream()
                        .map(permissionMapper::toInactiveResponse)
                        .toList())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @Override
    public ResponseEntity<PermissionResponseDto> findById(UUID id) {
        Optional<Permission> permission = permissionService.findByIdActive(id);
        return permission
                .map(permissionMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<PermissionResponseDto> save(PermissionRequestDto requestDto) {
        Permission permission = permissionMapper.toModel(requestDto);
        Permission result = permissionService.save(permission);

        return Objects.isNull(result)
                ? ResponseEntity.status(400).build()
                : ResponseEntity.status(201).body(permissionMapper.toResponse(result));
    }

    @Override
    public ResponseEntity<PermissionResponseDto> update(PermissionRequestDto requestDto, UUID id) {
        Permission newPermission = permissionMapper.toModel(requestDto);
        Permission result = permissionService.update(newPermission, id);

        return Objects.isNull(result)
                ? ResponseEntity.status(400).build()
                : ResponseEntity.status(200).body(permissionMapper.toResponse(result));
    }

    @Override
    public ResponseEntity<Void> deleteById(UUID id) {
        permissionService.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    @Override
    public ResponseEntity<InactivePermissionResponseDto> softDeleteById(UUID id) {
        Permission permission = permissionService.softDeleteById(id);
        return ResponseEntity.status(200).body(
                permissionMapper.toInactiveResponse(permission)
        );
    }

    @Override
    public ResponseEntity<PermissionResponseDto> undeleteById(UUID id) {
        Permission permission = permissionService.undeleteById(id);
        return ResponseEntity.status(200).body(
                permissionMapper.toResponse(permission)
        );
    }
}
