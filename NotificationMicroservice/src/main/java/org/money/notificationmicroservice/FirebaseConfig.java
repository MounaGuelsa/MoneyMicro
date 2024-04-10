package org.money.notificationmicroservice;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
@Configuration
public class FirebaseConfig {
    // Load the Firebase configuration file
    ClassLoader classLoader = NotificationMicroserviceApplication.class.getClassLoader();
    File file = new File(classLoader.getResource("notification-572a9-firebase-adminsdk-zbdnc-7499ff0eab.json").getFile());
    FileInputStream serviceAccount;
    FirebaseOptions options = null;

    /**
     * This constructor initializes the Firebase Realtime Database.
     */
    public FirebaseConfig() {
        try {
            serviceAccount = new FileInputStream(file.getAbsolutePath());
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://notification-572a9-default-rtdb.firebaseio.com/")
                    .build();
            if (options != null) {
                FirebaseApp.initializeApp(options);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}