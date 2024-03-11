package org.money.notificationmicroservice;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDTO {
    private Long idNotif;

    @NotBlank(message = "Contenu de notification ne peut pas être vide")
    private String contenuNotif;

    private boolean lu;

    @NotNull(message = "Date de création de notification ne peut pas être vide")
    private LocalDateTime dateCreationNotif;

    //@NotNull(message = "L'identifiant de l'utilisateur ne peut pas être vide")
    private Long utilisateurId;

}
