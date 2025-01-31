package com.demo.controllers;

import com.demo.dto.EventRequest;
import com.demo.services.GoogleCalendarService;
import com.demo.services.GoogleCalendarService1;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("application")
public class CalendarController {

    @Autowired
    private final GoogleCalendarService googleCalendarService;

    private final Map<String, EventRequest> eventCache = new ConcurrentHashMap<>();

    public CalendarController(GoogleCalendarService googleCalendarService) {
        this.googleCalendarService = googleCalendarService;
    }

//    @GetMapping("/auth-url")
//    public Map<String, String> getAuthUrl() throws IOException, GeneralSecurityException {
//        String authUrl = googleCalendarService.getAuthUrl();
//        Map<String, String> response = new HashMap<>();
//        response.put("authUrl", authUrl);
//        return response;
//    }
//
//    @PostMapping("create-event")
//    public String createEvent(@RequestBody EventRequest eventRequest) throws Exception {
//        return googleCalendarService.createEvent(
//                eventRequest.getSummary(),
//                eventRequest.getLocation(),
//                eventRequest.getDescription(),
//                eventRequest.getStartDateTime().toInstant().toString(),
//                eventRequest.getEndDateTime().toInstant().toString()
//        );
//    }
    @GetMapping("/auth-url")
    public Map<String, String> getAuthUrl() throws IOException, GeneralSecurityException {
        String authUrl = googleCalendarService.getAuthUrl();
        Map<String, String> response = new HashMap<>();
        response.put("authUrl", authUrl);
        return response;
    }

    @GetMapping("/check-auth")
    public boolean checkUserAuthentication() {
        return googleCalendarService.isUserAuthenticated();
    }

    @PostMapping("/oauth-callback")
    public ResponseEntity<Map<String, String>> handleOAuthCallback(@RequestParam("code") String code) throws IOException, GeneralSecurityException {
        googleCalendarService.exchangeCodeForToken(code);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Authentication successful!");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/oauth-callback")
    public ResponseEntity<ResponseEntity<String>> handleOAuthCallback(@RequestParam("code") String code, HttpServletResponse response) throws IOException, GeneralSecurityException {
        googleCalendarService.exchangeCodeForToken(code);

        // 🔹 Sau khi xác thực, redirect về trang Angular
        response.sendRedirect("http://localhost:4200/employer/shortlist-seeker?authSuccess=true");

        return ResponseEntity.ok().build();
    }

    // 🔹 Lưu dữ liệu sự kiện trước khi xác thực OAuth
    @PostMapping("/save-event")
    public ResponseEntity<Map<String, String>> saveEventData(@RequestBody EventRequest eventRequest) {
        eventCache.put("eventData", eventRequest);
        // 🔹 Trả về JSON thay vì plain text
        Map<String, String> response = new HashMap<>();
        response.put("message", "Event data saved.");
        return ResponseEntity.ok(response);
    }

    // test commit

    // 🔹 Trả về dữ liệu sự kiện sau khi redirect
    @GetMapping("/get-saved-event")
    public ResponseEntity<EventRequest> getSavedEventData() {
        EventRequest eventData = eventCache.get("eventData");
        if (eventData != null) {
            return ResponseEntity.ok(eventData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/create-event")
    public ResponseEntity<Map<String, String>> createEvent(@RequestBody EventRequest eventRequest) throws Exception {
        String meetLink = googleCalendarService.createEvent(
                eventRequest.getSummary(),
                eventRequest.getLocation(),
                eventRequest.getDescription(),
                eventRequest.getStartDateTime().toInstant().toString(),
                eventRequest.getEndDateTime().toInstant().toString()
        );

        // 🔹 Trả về JSON thay vì chuỗi đơn giản
        Map<String, String> response = new HashMap<>();
        response.put("meetLink", meetLink);
        return ResponseEntity.ok(response);
    }

}