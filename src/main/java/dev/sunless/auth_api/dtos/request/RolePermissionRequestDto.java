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
public class RolePermissionRequestDto {

    @NotEmpty(message = "Permissions list cannot be empty")
    private Set<@NotNull(message = "Permission cannot be null") UUID> permissions;
}
