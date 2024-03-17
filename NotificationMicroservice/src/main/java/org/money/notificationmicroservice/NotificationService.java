package org.money.notificationmicroservice;

import java.util.List;

public interface NotificationService {
    public List<NotificationDTO> obtenirNotifications();
    NotificationDTO obtenirNotificationParId(Long id);
    NotificationDTO envoyerNotification(NotificationDTO notificationDTO);

    void supprimerNotification(Long id);
    void marquerCommeLu(Long id);
    List<NotificationDTO> obtenirNotificationsNonLues();
}
