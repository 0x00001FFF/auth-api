package dev.sunless.auth_api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InactiveRoleResponseDto extends BaseResponse {

    private UUID id;
    private String name;
    private String description;
    private Set<PermissionResponseDto> permissions;
    private LocalDateTime deletedAt;
}
