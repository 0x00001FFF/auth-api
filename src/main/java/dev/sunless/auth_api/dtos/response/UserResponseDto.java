package dev.sunless.auth_api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto extends BaseResponse {

    private UUID id;
    private String userName;
    private String email;
    private Set<SimpleRoleResponseDto> roles;
    private Boolean isActive;
}
