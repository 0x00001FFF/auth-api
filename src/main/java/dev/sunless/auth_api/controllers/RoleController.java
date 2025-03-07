package dev.sunless.auth_api.controllers;

import dev.sunless.auth_api.dtos.request.RolePermissionRequestDto;
import dev.sunless.auth_api.dtos.request.RoleRequestDto;
import dev.sunless.auth_api.dtos.response.InactiveRoleResponseDto;
import dev.sunless.auth_api.dtos.response.RoleResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface RoleController {


    @GetMapping
    ResponseEntity<List<RoleResponseDto>> findAllActive();

    @GetMapping("/inactive")
    ResponseEntity<List<InactiveRoleResponseDto>> findAllInactive();

    @GetMapping("/{id}")
    ResponseEntity<RoleResponseDto> findById(@PathVariable UUID id);

    @PostMapping
    ResponseEntity<RoleResponseDto> save(@Valid @RequestBody RoleRequestDto requestDto);

    @PutMapping("{id}")
    ResponseEntity<RoleResponseDto> update(@Valid @RequestBody
                                                 RoleRequestDto requestDto,
                                                 @PathVariable UUID id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable UUID id);

    @PatchMapping("/{id}")
    ResponseEntity<InactiveRoleResponseDto> softDeleteById(@PathVariable UUID id);

    @PatchMapping("/undelete/{id}")
    ResponseEntity<RoleResponseDto> undeleteById(@PathVariable UUID id);

    @PatchMapping("/permissions/{id}")
    ResponseEntity<RoleResponseDto> updatePermissions(@Valid @RequestBody
                                                      RolePermissionRequestDto permissionRequestDto,
                                                      @PathVariable UUID id);
}
