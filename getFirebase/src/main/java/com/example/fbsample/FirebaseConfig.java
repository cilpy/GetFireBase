package com.example.fbsample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class FirebaseConfig {

	FirebaseConfig() throws FileNotFoundException, IOException{
		initFirebaseApp();
	}

	public void initFirebaseApp() throws FileNotFoundException, IOException{
		if(FirebaseApp.getApps().isEmpty()) {
			FileInputStream serviceAccount =
					  new FileInputStream(
							  new ClassPathResource("test1-c06c1-firebase-adminsdk-u1qy6-bfb62f2b99.json").getFile());

			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl("https://test1-c06c1.firebaseio.com")
					  .build();

			FirebaseApp.initializeApp(options);
		}
	}
}
