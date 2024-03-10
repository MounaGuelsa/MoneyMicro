package org.money.notificationmicroservice.dtos;



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


    private String contenuNotif;
    private boolean Lu;
    private LocalDateTime dateCreationNotif;
    private Long  destinataireId;
}
