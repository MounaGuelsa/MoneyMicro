package org.money.notificationmicroservice;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class NotificationServiceImp implements  NotificationService{
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImp.class);

    @Override
    public void supprimmerNotification(String id) {
        // Initialize Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference notificationsRef = database.getReference("notifications");
        LOGGER.debug("Notification ID: {}", id);

        // Delete the notification from Firebase Realtime Database
        notificationsRef.child(id).removeValueAsync();
        LOGGER.info("Notification deleted successfully");
    }

    @Override
    public void envoyerNotification(Notification notification) {
        // Initialize Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference notificationsRef = database.getReference("notifications");

        // Generate a unique key for the notification
        String key = notificationsRef.push().getKey();


        // Prepare the notification data
        Map<String, Object> notificationData = new HashMap<>();
        notificationData.put("contenuNotif", notification.getContenuNotif());
        notificationData.put("dateCreationNotif",LocalDate.now().toString() );

        LOGGER.debug("Notification data: {}", notificationData);

        notificationsRef.child(key).setValueAsync(notificationData);
        LOGGER.info("Notification sent successfully");
    }
    @Scheduled(cron = "0 0 20 * * ?")
    @Override
    public void envoyerNotificationQuotidienne() {
        Notification notification = new Notification();
        notification.setContenuNotif("N'oubliez pas d'ajouter vos dépenses du jour !");

        envoyerNotification(notification);
        LOGGER.info("Notification quotidienne envoyée à 20:00h");
    }
}
