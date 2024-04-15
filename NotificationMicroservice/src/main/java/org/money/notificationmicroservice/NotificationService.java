package org.money.notificationmicroservice;


import org.springframework.scheduling.annotation.Scheduled;

public interface NotificationService {

    void supprimmerNotification(String id);


    void envoyerNotification(Notification notification);

    @Scheduled(cron = "0 0 20 * * ?")
    void envoyerNotificationQuotidienne();
}
