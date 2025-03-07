package dev.sunless.auth_api.dtos.response;

import dev.sunless.auth_api.models.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseDto extends BaseResponse {

    private String name;
    private String description;
    private Set<Permission> permissions;
}
