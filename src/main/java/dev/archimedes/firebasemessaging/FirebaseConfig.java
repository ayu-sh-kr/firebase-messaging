package dev.archimedes.firebasemessaging;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseConfig {

  @Value("${fcm.firebase.service-account}")
  String serviceAccountPath;

  @Bean
  GoogleCredentials credentials() {
    ClassPathResource resource = new ClassPathResource(serviceAccountPath);
    try(InputStream stream = resource.getInputStream()) {
      return GoogleCredentials.fromStream(stream);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Bean
  FirebaseApp firebaseApp(GoogleCredentials credentials) {
    FirebaseOptions firebaseOptions = FirebaseOptions.builder()
        .setCredentials(credentials)
        .build();

    return FirebaseApp.initializeApp(firebaseOptions);
  }

  @Bean
  FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
    return FirebaseMessaging.getInstance(firebaseApp);
  }

}
