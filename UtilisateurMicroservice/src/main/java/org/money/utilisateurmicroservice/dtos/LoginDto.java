package org.money.utilisateurmicroservice.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto {
    @NotBlank
    String nomUtilisateur ;
    @NotBlank
    String password;
}
