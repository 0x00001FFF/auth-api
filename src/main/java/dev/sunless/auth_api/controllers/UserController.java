package dev.sunless.auth_api.controllers;

import dev.sunless.auth_api.dtos.request.UserRequestDto;
import dev.sunless.auth_api.dtos.request.UserRoleRequestDto;
import dev.sunless.auth_api.dtos.response.InactiveUserResponseDto;
import dev.sunless.auth_api.dtos.response.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface UserController {

    @GetMapping
    ResponseEntity<List<UserResponseDto>> findAllActive();

    @GetMapping("/inactive")
    ResponseEntity<List<InactiveUserResponseDto>> findAllInactive();

    @GetMapping("/{id}")
    ResponseEntity<UserResponseDto> findById(@PathVariable UUID id);

    @PostMapping
    ResponseEntity<UserResponseDto> save(@Valid @RequestBody UserRequestDto requestDto);

    @PutMapping("{id}")
    ResponseEntity<UserResponseDto> update(@Valid @RequestBody
                                           UserRequestDto requestDto,
                                           @PathVariable UUID id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable UUID id);

    @PatchMapping("/{id}")
    ResponseEntity<InactiveUserResponseDto> softDeleteById(@PathVariable UUID id);

    @PatchMapping("/undelete/{id}")
    ResponseEntity<UserResponseDto> undeleteById(@PathVariable UUID id);

    @PatchMapping("/roles/{id}")
    ResponseEntity<UserResponseDto> updateRoles(@Valid @RequestBody
                                                      UserRoleRequestDto userRoleRequestDto,
                                                      @PathVariable UUID id);
}
