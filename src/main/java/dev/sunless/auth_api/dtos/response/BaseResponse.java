package dev.sunless.auth_api.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseResponse {

    private LocalDateTime createdDate;
    private  LocalDateTime lastModified;
}
