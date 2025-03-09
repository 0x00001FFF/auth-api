package dev.sunless.auth_api.services.impl;

import dev.sunless.auth_api.dtos.request.UserUpdateDto;
import dev.sunless.auth_api.exceptions.DuplicatedException;
import dev.sunless.auth_api.exceptions.NotFoundException;
import dev.sunless.auth_api.models.User;
import dev.sunless.auth_api.repositories.UserRepository;
import dev.sunless.auth_api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user, Set<UUID> roles) {
        validateUserDuplicity(user);
        validateUserRoles(roles.size(), user.getRoles().size());
        user.setIsActive(false);
        return userRepository.save(user);
    }

    @Override
    public User update(UserUpdateDto dto) {
        User user = userExistsOrThrow(dto.getId());
        User newUser = dto.getUser();
        if (!Objects.equals(user.getEmail(), newUser.getEmail()) &&
                userRepository.findByEmail(newUser.getEmail()).isPresent())
            throw new DuplicatedException("Cannot update user email to %s because this email"
                    .formatted(newUser.getEmail()));
        if (!Objects.equals(user.getUserName(), newUser.getUserName()) &&
                userRepository.existsByUserName(newUser.getUserName()))
            throw new DuplicatedException("Cannot update username to %s because this username"
                    .formatted(newUser.getUserName()));

        validateUserRoles(dto.getRoles().size(), newUser.getRoles().size());
        LocalDateTime createdDate = user.getCreatedDate();
        BeanUtils.copyProperties(newUser, user, "id", "createdDate", "isActive");
        user.setCreatedDate(createdDate);
        return userRepository.save(user);
    }

    private void validateUserRoles(int expected, int actual) {
        if (expected != actual)
            throw new IllegalArgumentException("There are invalid roles in the request");
    }

    private void validateUserDuplicity(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent())
            throw new DuplicatedException("User with E-mail %s".formatted(user.getEmail()));
        if (userRepository.existsByUserName(user.getUserName()))
            throw new DuplicatedException("User with username %s".formatted(user.getUserName()));
    }

    @Override
    public User updateRoles(UUID id, Set<UUID> roles) {
        return null;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByIdActive(UUID id) {
        return userRepository.findByIdActive(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAllActive();
    }

    @Override
    public List<User> findAllInactive() {
        return userRepository.findAllInactive();
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public User softDeleteById(UUID id) {
        return null;
    }

    @Override
    public User undeleteById(UUID id) {
        return null;
    }


    private User userExistsOrThrow(UUID id) {
        return findById(id).orElseThrow(
                () -> new NotFoundException("Role with ID: " + id)
        );
    }
}
