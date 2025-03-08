package dev.sunless.auth_api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequestDto {

    @NotBlank
    @Size(min = 4, max = 50, message = "The role name cannot be under {min} characters or over {max} characters")
    private String name;

    @Size(max = 255, message = "The role description cannot be over {max} characters")
    private String description;

    @NotNull
    private Set<@NotNull(message = "Permission cannot be null") UUID> permissions;

}
