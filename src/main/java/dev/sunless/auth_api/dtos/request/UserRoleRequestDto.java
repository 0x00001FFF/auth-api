package dev.sunless.auth_api.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleRequestDto {

    @NotNull(message = "User roles cannot be null")
    @NotEmpty(message = "Users must be assigned to at least a single role")
    private Set<@NotNull UUID> roles;
}
