package dev.sunless.auth_api.controllers;

import dev.sunless.auth_api.dtos.request.PermissionRequestDto;
import dev.sunless.auth_api.dtos.response.InactivePermissionResponseDto;
import dev.sunless.auth_api.dtos.response.PermissionResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface PermissionController {

    @GetMapping
    ResponseEntity<List<PermissionResponseDto>> findAllActive();

    @GetMapping("/inactive")
    ResponseEntity<List<InactivePermissionResponseDto>> findAllInactive();

    @GetMapping("/{id}")
    ResponseEntity<PermissionResponseDto> findById(@PathVariable UUID id);

    @PostMapping
    ResponseEntity<PermissionResponseDto> save(@Valid @RequestBody PermissionRequestDto requestDto);

    @PutMapping("{id}")
    ResponseEntity<PermissionResponseDto> update(@Valid @RequestBody
                                                 PermissionRequestDto requestDto,
                                                 @PathVariable UUID id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable UUID id);

    @PatchMapping("/{id}")
    ResponseEntity<InactivePermissionResponseDto> softDeleteById(@PathVariable UUID id);

    @PatchMapping("/undelete/{id}")
    ResponseEntity<PermissionResponseDto> undeleteById(@PathVariable UUID id);

}
