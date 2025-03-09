package dev.sunless.auth_api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InactiveUserResponseDto extends BaseResponse {


    private String userName;
    private String email;
    private Set<SimpleRoleResponseDto> roles;
    private Boolean isActive;
    private LocalDateTime deletedAt;
}
