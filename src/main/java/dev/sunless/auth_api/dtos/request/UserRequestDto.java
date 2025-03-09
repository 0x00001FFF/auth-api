package dev.sunless.auth_api.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @Size(min = 3, max = 50, message = "The user name cannot be under {min} characters or over {max} characters")
    @NotBlank(message = "User name cannot be blank")
    private String userName;

    @Email
    @Size(max = 255, message = "The user E-mail cannot have more than {max} characters")
    private String email;

    @NotNull(message = "User roles cannot be null")
    @NotEmpty(message = "New users must be assigned to at least a single role")
    private Set<@NotNull UUID> roles;

    @NotBlank(message = "User password cannot be black")
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}|:;\"'<>,.?/~`]).{8,}$",
            message = "Password must have at least 8 characters, one number, and one special character"
    )
    @Size(max = 255, message = "Passwords cannot be over {max} characters long")
    private String password;
}
