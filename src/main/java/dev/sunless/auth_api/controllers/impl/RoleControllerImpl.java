package dev.sunless.auth_api.controllers.impl;

import dev.sunless.auth_api.controllers.RoleController;
import dev.sunless.auth_api.dtos.request.RolePermissionRequestDto;
import dev.sunless.auth_api.dtos.request.RoleRequestDto;
import dev.sunless.auth_api.dtos.response.InactiveRoleResponseDto;
import dev.sunless.auth_api.dtos.response.RoleResponseDto;
import dev.sunless.auth_api.mappers.RoleMapper;
import dev.sunless.auth_api.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
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
        return null;
    }

    @Override
    public ResponseEntity<List<InactiveRoleResponseDto>> findAllInactive() {
        return null;
    }

    @Override
    public ResponseEntity<RoleResponseDto> findById(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<RoleResponseDto> save(RoleRequestDto requestDto) {
        return null;
    }

    @Override
    public ResponseEntity<RoleResponseDto> update(RoleRequestDto requestDto, UUID id) {
        return null;
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
