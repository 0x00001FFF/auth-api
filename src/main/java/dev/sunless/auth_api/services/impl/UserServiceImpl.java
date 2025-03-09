package dev.sunless.auth_api.services.impl;

import dev.sunless.auth_api.exceptions.DuplicatedException;
import dev.sunless.auth_api.models.User;
import dev.sunless.auth_api.repositories.UserRepository;
import dev.sunless.auth_api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user, Set<UUID> roles) {
        if(userRepository.findByEmail(user.getEmail()).isPresent())
            throw new DuplicatedException("User with E-mail %s".formatted(user.getEmail()));

        if (userRepository.existsByUserName(user.getUserName()))
            throw new DuplicatedException("User with username %s".formatted(user.getUserName()));

        if (user.getRoles().size() != roles.size())
            throw new IllegalArgumentException("There are invalid roles in the request");
        user.setIsActive(false);
        return userRepository.save(user);
    }

    @Override
    public User update(User newUser, UUID id) {
        return null;
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
}
