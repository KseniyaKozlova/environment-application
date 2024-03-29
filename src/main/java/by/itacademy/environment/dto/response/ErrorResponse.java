package by.itacademy.environment.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {

    private String message;

    private String error;

    private LocalDateTime time;

    private HttpStatus httpStatus;
}
