package dev.sunless.auth_api.exceptions;

import dev.sunless.auth_api.dtos.response.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedPermissionException.class)
    public ResponseEntity<ErrorResponseDTO> handleDuplicatedPermissionException(DuplicatedPermissionException e) {
        ErrorResponseDTO responseDTO = new ErrorResponseDTO("409",
                new ErrorResponseDTO.ErrorContent(e.getMessage(), LocalDateTime.now())
        );
        return ResponseEntity.status(409).body(responseDTO);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(NotFoundException e) {
        ErrorResponseDTO responseDTO = new ErrorResponseDTO("404",
                new ErrorResponseDTO.ErrorContent(e.getMessage(), LocalDateTime.now())
        );
        return ResponseEntity.status(404).body(responseDTO);
    }


    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalStateException(IllegalStateException e) {
        ErrorResponseDTO responseDTO = new ErrorResponseDTO("409",
                new ErrorResponseDTO.ErrorContent(e.getMessage(), LocalDateTime.now())
        );
        return ResponseEntity.status(409).body(responseDTO);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException e) {
        ErrorResponseDTO responseDTO = new ErrorResponseDTO("400",
                new ErrorResponseDTO.ErrorContent(e.getMessage(), LocalDateTime.now())
        );
        return ResponseEntity.status(400).body(responseDTO);
    }

}
