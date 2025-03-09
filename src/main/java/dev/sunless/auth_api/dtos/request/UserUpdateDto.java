package dev.sunless.auth_api.dtos.request;

import dev.sunless.auth_api.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

    private User user;
    private Set<UUID> roles;
    private UUID id;
}
