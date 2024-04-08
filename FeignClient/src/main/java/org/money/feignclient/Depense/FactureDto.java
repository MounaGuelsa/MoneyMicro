package org.money.feignclient.Depense;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;


@Value
@Builder

public class FactureDto implements Serializable {
    @NotBlank
    Long numeroFacture;
    @NotBlank
    String nomFacture;
    //@NotNull
    DepenseDto depense;
    byte[] url;

}