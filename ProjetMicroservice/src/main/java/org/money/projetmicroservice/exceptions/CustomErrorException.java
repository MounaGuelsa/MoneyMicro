package org.money.projetmicroservice.exceptions;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


import java.time.LocalDateTime;
@Getter
@Setter
public class CustomErrorException {
    private HttpStatus status;
    private String error;
    private LocalDateTime timestamp;
}
