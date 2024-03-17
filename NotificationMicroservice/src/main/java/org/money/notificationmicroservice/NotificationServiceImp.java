package org.money.notificationmicroservice;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NotificationServiceImp implements  NotificationService{
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImp.class);



    @Override
    public List<NotificationDTO> obtenirNotifications() {
        try {
            List<Notification> notifications = notificationRepository.findAll();
            return notifications.stream()
                    .map(notificationMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la récupération des notifications : {}", e.getMessage());
            throw new RuntimeException("Erreur lors de la récupération des notifications", e);
        }
    }

    @Override
    public NotificationDTO obtenirNotificationParId(Long id) {
        try {
            Optional<Notification> notificationOptional = notificationRepository.findById(id);
            return notificationOptional.map(notificationMapper::toDTO).orElse(null);
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la récupération de la notification avec l'ID {} : {}", id, e.getMessage());
            throw new RuntimeException("Erreur lors de la récupération de la notification par ID", e);
        }
    }

    @Override
    public NotificationDTO envoyerNotification(NotificationDTO notificationDTO) {
        try {
            Notification notification = notificationMapper.toEntity(notificationDTO);
            notification = notificationRepository.save(notification);
            return notificationMapper.toDTO(notification);
        } catch (Exception e) {
            LOGGER.error("Erreur lors de l'e.nvoi de la notification : {}", e.getMessage());
            throw new RuntimeException("Erreur lors de l'envoi de la notification", e);
        }
    }

    @Override
    public void supprimerNotification(Long id) {
        try {
            notificationRepository.deleteById(id);
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la suppression de la notification avec l'ID {} : {}", id, e.getMessage());
            throw new RuntimeException("Erreur lors de la suppression de la notification", e);
        }
    }

    @Override
    public void marquerCommeLu(Long id) {
        try {
            Optional<Notification> notificationOptional = notificationRepository.findById(id);
            notificationOptional.ifPresent(notification -> {
                notification.setLu(true);
                notificationRepository.save(notification);
            });
        } catch (Exception e) {
            LOGGER.error("Erreur lors du marquage de la notification comme lue avec l'ID {} : {}", id, e.getMessage());
            throw new RuntimeException("Erreur lors du marquage de la notification comme lue", e);
        }
    }

    @Override
    public List<NotificationDTO> obtenirNotificationsNonLues() {
        try {
            List<Notification> notifications = notificationRepository.findByLuFalse();
            return notifications.stream()
                    .map(notificationMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la récupération des notifications non lues : {}", e.getMessage());
            return null;        }
    }
}
