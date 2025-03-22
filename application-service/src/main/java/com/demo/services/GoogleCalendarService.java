package com.demo.services;

import com.demo.dto.InterviewDTO;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.ConferenceData;
import com.google.api.services.calendar.model.CreateConferenceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleCalendarService {
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/calendar");
    private static final String REDIRECT_URI = "http://localhost:8080/application/oauth-callback";

    // 🔹 File lưu token trong resources (chạy local) hoặc thư mục bên ngoài (khi deploy)
    private static final String TOKEN_FILE_PATH = "src/main/resources/client_secret.json"; // Local
    private static final String TOKEN_FILE_DEPLOY = System.getProperty("user.home") + "/google_tokens.json"; // Deploy

    @Autowired
    private InterviewService interviewService;

    public boolean isUserAuthenticated() {
        File tokenFile = new File(TOKEN_FILE_PATH);
        return tokenFile.exists() && tokenFile.length() > 0;
    }

    private GoogleAuthorizationCodeFlow getFlow() throws IOException, GeneralSecurityException {
        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JSON_FACTORY,
                new InputStreamReader(new ClassPathResource("client_secret.json").getInputStream())
        );

        return new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setAccessType("offline")
                .build();
    }

    public String getAuthUrl() throws IOException, GeneralSecurityException {
        GoogleAuthorizationCodeFlow flow = getFlow();
        return flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
    }

    public Credential exchangeCodeForToken(String code) throws IOException, GeneralSecurityException {
        GoogleAuthorizationCodeFlow flow = getFlow();
        GoogleTokenResponse tokenResponse = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
        Credential credential = flow.createAndStoreCredential(tokenResponse, null);

        // 🔹 Lưu token vào file
        saveToken(credential);
        return credential;
    }

    private void saveToken(Credential credential) throws IOException {
        File tokenFile = getTokenFile();
        tokenFile.getParentFile().mkdirs(); // Tạo thư mục nếu chưa có
        try (FileWriter writer = new FileWriter(tokenFile)) {
            writer.write(credential.getAccessToken());
        }
    }

    private String loadToken() throws IOException {
        File tokenFile = getTokenFile();
        if (!tokenFile.exists()) {
            return null;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(tokenFile))) {
            return reader.readLine();
        }
    }

    private File getTokenFile() {
        File tokenFile = new File(TOKEN_FILE_PATH);
        if (!tokenFile.exists() || !tokenFile.canWrite()) {
            tokenFile = new File(TOKEN_FILE_DEPLOY); // Nếu không ghi vào resources, dùng thư mục ngoài
        }
        return tokenFile;
    }

    public Calendar getCalendarService() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        String accessToken = loadToken();

        if (accessToken == null) {
            throw new IOException("User is not authenticated. Please authenticate first.");
        }

        Credential credential = new Credential.Builder(BearerToken.authorizationHeaderAccessMethod())
                .setJsonFactory(JSON_FACTORY)
                .setTransport(HTTP_TRANSPORT)
                .setTokenServerEncodedUrl("https://oauth2.googleapis.com/token")
                .build()
                .setAccessToken(accessToken);

        return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    // 🔹 Hàm Tạo Google Meet (createEvent)
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

        // 🔹 Thêm Google Meet link
        event.setConferenceData(new ConferenceData()
                .setCreateRequest(new CreateConferenceRequest()
                        .setRequestId("meeting-" + System.currentTimeMillis()))); // ID duy nhất

        // 🔹 Chèn sự kiện vào Google Calendar
        Event createdEvent = service.events()
                .insert("primary", event)
                .setConferenceDataVersion(1) // Bắt buộc để kích hoạt Google Meet
                .execute();

        // 🔹 Lấy link Google Meet
        if (createdEvent.getConferenceData() != null &&
                createdEvent.getConferenceData().getEntryPoints() != null &&
                !createdEvent.getConferenceData().getEntryPoints().isEmpty()) {
           
            return createdEvent.getConferenceData().getEntryPoints().get(0).getUri();
        } else {
            throw new IOException("Failed to generate Google Meet link.");
        }
    }
}
