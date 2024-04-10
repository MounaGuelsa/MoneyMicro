package org.money.notificationmicroservice;



public interface NotificationService {

    void supprimmerNotification(String id);


    void envoyerNotification(Notification notification);
}
