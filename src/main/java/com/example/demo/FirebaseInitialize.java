package com.example.demo;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

@Service
public class FirebaseInitialize {

    @PostConstruct
    public void initialize() {
        try {
            File file = new File(
                    Objects.requireNonNull(getClass().getClassLoader()
                            .getResource("pick-for-me-6c9bf-firebase-adminsdk-wqrj4-6c472fc1e1.json")).getFile()
            );

            FileInputStream serviceAccount =
                    new FileInputStream(file);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://pick-for-me-6c9bf-default-rtdb.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
