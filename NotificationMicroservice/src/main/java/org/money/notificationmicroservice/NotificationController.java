package org.money.notificationmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> obtenirNotifications() {
        List<NotificationDTO> notifications = notificationService.obtenirNotifications();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> obtenirNotificationParId(@PathVariable Long id) {
        NotificationDTO notification = notificationService.obtenirNotificationParId(id);
        if (notification != null) {
            return new ResponseEntity<>(notification, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<NotificationDTO> envoyerNotification(@RequestBody NotificationDTO notificationDTO) {
        NotificationDTO createdNotification = notificationService.envoyerNotification(notificationDTO);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationDTO> modifierNotification(@PathVariable Long id, @RequestBody NotificationDTO notificationDTO) {
        NotificationDTO modifiedNotification = notificationService.modifierNotification(id, notificationDTO);
        if (modifiedNotification != null) {
            return new ResponseEntity<>(modifiedNotification, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerNotification(@PathVariable Long id) {
        notificationService.supprimerNotification(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/marquerCommelu")
    public ResponseEntity<Void> marquerCommeLu(@PathVariable Long id) {
        notificationService.marquerCommeLu(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/nonLues")
    public ResponseEntity<List<NotificationDTO>> obtenirNotificationsNonLues() {
        List<NotificationDTO> nonLues = notificationService.obtenirNotificationsNonLues();
        return new ResponseEntity<>(nonLues, HttpStatus.OK);
    }
}
