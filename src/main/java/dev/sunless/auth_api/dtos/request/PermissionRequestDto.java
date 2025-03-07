package dev.sunless.auth_api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequestDto {

    @NotBlank
    @Size(min = 4, max = 50, message = "The permission name cannot be under {min} characters or over {max} characters")
    private String name;
}
