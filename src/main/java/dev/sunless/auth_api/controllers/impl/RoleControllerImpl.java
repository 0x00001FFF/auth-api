package dev.sunless.auth_api.controllers.impl;

import dev.sunless.auth_api.controllers.RoleController;
import dev.sunless.auth_api.dtos.request.RolePermissionRequestDto;
import dev.sunless.auth_api.dtos.request.RoleRequestDto;
import dev.sunless.auth_api.dtos.response.InactiveRoleResponseDto;
import dev.sunless.auth_api.dtos.response.RoleResponseDto;
import dev.sunless.auth_api.mappers.RoleMapper;
import dev.sunless.auth_api.models.Role;
import dev.sunless.auth_api.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping(RoleControllerImpl.BASE_URL)
public class RoleControllerImpl implements RoleController {

    public static final String BASE_URL = "roles";
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @Override
    public ResponseEntity<List<RoleResponseDto>> findAllActive() {
        List<Role> roles = roleService.findAll();

        return Optional.of(roles)
                .filter(list -> !list.isEmpty())
                .map(list -> list
                        .stream()
                        .map(roleMapper::toResponse)
                        .toList())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @Override
    public ResponseEntity<List<InactiveRoleResponseDto>> findAllInactive() {
        List<Role> roles = roleService.findAllInactive();

        return Optional.of(roles)
                .filter(list -> !list.isEmpty())
                .map(list -> list
                        .stream()
                        .map(roleMapper::toInactiveResponse)
                        .toList())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @Override
    public ResponseEntity<RoleResponseDto> findById(UUID id) {
        Optional<Role> role = roleService.findByIdActive(id);
        return role
                .map(roleMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).build());
    }

    @Override
    public ResponseEntity<RoleResponseDto> save(RoleRequestDto requestDto) {
        Role role = roleMapper.toModel(requestDto);
        Role result = roleService.save(role);

        return Objects.isNull(result)
                ? ResponseEntity.status(400).build()
                : ResponseEntity.status(201).body(roleMapper.toResponse(result));
    }

    @Override
    public ResponseEntity<RoleResponseDto> update(RoleRequestDto requestDto, UUID id) {
        Role newPermission = roleMapper.toModel(requestDto);
        Role result = roleService.update(newPermission, id);

        return Objects.isNull(result)
                ? ResponseEntity.status(400).build()
                : ResponseEntity.status(200).body(roleMapper.toResponse(result));
    }

    @Override
    public ResponseEntity<Void> deleteById(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<InactiveRoleResponseDto> softDeleteById(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<RoleResponseDto> undeleteById(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<RoleResponseDto> updatePermissions(RolePermissionRequestDto permissionRequestDto, UUID id) {
        return null;
    }
}
