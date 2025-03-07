package dev.sunless.auth_api.dtos.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InactivePermissionResponseDto extends BaseResponse {

    private UUID id;
    private String name;
    private LocalDateTime deletedAt;
}
