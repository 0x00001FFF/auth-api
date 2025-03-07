package dev.sunless.auth_api.dtos.response;

import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponseDto extends BaseResponse {

    private UUID id;
    private String name;
}
