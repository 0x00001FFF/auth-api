package dev.sunless.auth_api.controllers.impl;

import dev.sunless.auth_api.controllers.UserController;
import dev.sunless.auth_api.dtos.request.UserRequestDto;
import dev.sunless.auth_api.dtos.request.UserRoleRequestDto;
import dev.sunless.auth_api.dtos.response.InactiveUserResponseDto;
import dev.sunless.auth_api.dtos.response.UserResponseDto;
import dev.sunless.auth_api.mappers.UserMapper;
import dev.sunless.auth_api.models.Role;
import dev.sunless.auth_api.models.User;
import dev.sunless.auth_api.services.RoleService;
import dev.sunless.auth_api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping(UserControllerImpl.BASE_URL)
public class UserControllerImpl implements UserController {

    public static final String BASE_URL = "users";
    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleService roleService;

    @Override
    public ResponseEntity<List<UserResponseDto>> findAllActive() {
        return null;
    }

    @Override
    public ResponseEntity<List<InactiveUserResponseDto>> findAllInactive() {
        return null;
    }

    @Override
    public ResponseEntity<UserResponseDto> findById(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<UserResponseDto> save(UserRequestDto requestDto) {
        Set<Role> roles = roleService.findByIdIn(requestDto.getRoles());
        User user = userMapper.toModel(requestDto, roles);
        User result = userService.save(user, requestDto.getRoles());

        return Objects.isNull(result)
                ? ResponseEntity.status(400).build()
                : ResponseEntity.status(201).body(userMapper.toResponse(result));
    }

    @Override
    public ResponseEntity<UserResponseDto> update(UserRequestDto requestDto, UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteById(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<InactiveUserResponseDto> softDeleteById(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<UserResponseDto> undeleteById(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<UserResponseDto> updateRoles(UserRoleRequestDto userRoleRequestDto, UUID id) {
        return null;
    }
}
