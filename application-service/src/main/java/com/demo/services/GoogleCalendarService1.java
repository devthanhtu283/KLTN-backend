package com.demo.services;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleCalendarService1 {
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/calendar");
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final String CLIENT_ID = "746728265917-3uhi6rb07ho078g55s3rbaffsk6p8saq.apps.googleusercontent.com";
    private static final String REDIRECT_URI = "http://localhost:9000/Callback"; // Redirect về Frontend

    public String getAuthUrl() throws IOException, GeneralSecurityException {
        return new GoogleAuthorizationCodeRequestUrl(CLIENT_ID, REDIRECT_URI, SCOPES)
                .setAccessType("offline")
                .build();
    }

    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JSON_FACTORY,
                new InputStreamReader(new ClassPathResource("client_secret.json").getInputStream())
        );
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setAccessType("offline")
                .build();

        return new AuthorizationCodeInstalledApp(
                flow,
                new LocalServerReceiver.Builder().setPort(9000).build() // Cố định port 8000
        ).authorize("user");
    }

    public Calendar getCalendarService() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = getCredentials(HTTP_TRANSPORT);

        return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public String createEvent(String summary, String location, String description, String startDateTime, String endDateTime) throws IOException, GeneralSecurityException {
        Calendar service = getCalendarService();

        Event event = new Event()
                .setSummary(summary)
                .setLocation(location)
                .setDescription(description);

        EventDateTime start = new EventDateTime()
                .setDateTime(new com.google.api.client.util.DateTime(startDateTime));
        event.setStart(start);

        EventDateTime end = new EventDateTime()
                .setDateTime(new com.google.api.client.util.DateTime(endDateTime));
        event.setEnd(end);

//        Event createdEvent = service.events().insert("primary", event).execute();

        // 🔹 Thêm Google Meet link
        event.setConferenceData(new com.google.api.services.calendar.model.ConferenceData()
                .setCreateRequest(new com.google.api.services.calendar.model.CreateConferenceRequest()
                        .setRequestId("meeting-" + System.currentTimeMillis()))); // ID duy nhất

        // 🔹 Chèn sự kiện vào Google Calendar
        Event createdEvent = service.events()
                .insert("primary", event)
                .setConferenceDataVersion(1) // Bắt buộc để kích hoạt Google Meet
                .execute();

        // 🔹 Lấy link Google Meet
        String meetLink = createdEvent.getConferenceData().getEntryPoints().get(0).getUri();
        return meetLink;
    }
}
