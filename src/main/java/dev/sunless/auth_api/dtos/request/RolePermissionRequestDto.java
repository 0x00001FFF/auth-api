package dev.sunless.auth_api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionRequestDto {

    @NotEmpty(message = "Permissions list cannot be empty")
    @Size(min = 1, message = "At least one permission must be provided")
    private List<@NotBlank(message = "Permission cannot be blank") UUID> permissions;
}
