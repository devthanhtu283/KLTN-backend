package com.demo.controllers;

import com.demo.configurations.JwtUtils;
import com.demo.dtos.UserDTO;
import com.demo.helpers.LoginResponse;
import com.demo.services.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class GoogleLoginController {
    private static final Logger logger = LoggerFactory.getLogger(GoogleLoginController.class);
    @Autowired
    private UserService userService;
    private static final String CLIENT_ID = "154715425720-a22pc63jahbgsq5pnqs05rr9a4sa7q3e.apps.googleusercontent.com";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping(value = "google-login")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> requestBody) throws Exception {
        String idTokenString = requestBody.get("idToken");
        String userTypeString = requestBody.get("userType");

        int userType = 1;
        try {
            userType = Integer.parseInt(userTypeString);
        } catch (Exception e) {
            System.out.println("Không thể parse userType, dùng mặc định 1");
        }

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY)
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            String email = payload.getEmail();
            String name = (String) payload.get("name");

            // ✅ Kiểm tra user đã tồn tại chưa
            Optional<UserDTO> existingUserOpt = Optional.ofNullable(userService.findByEmail(email));

            UserDTO user;
            if (existingUserOpt.isPresent()) {
                user = existingUserOpt.get();
            } else {
                // ✅ Nếu chưa có thì tạo mới
                user = new UserDTO();
                user.setPassword("123"); // Dummy password
                user.setUsername(email.split("@")[0]);
                user.setEmail(email);
                user.setUserType(userType);
                user.setSecurityCode("google");
                user.setStatus(1);
                user.setCreated(new java.sql.Date(System.currentTimeMillis()));
                userService.save(user);
            }
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            final String jwt = jwtUtils.generateToken(userDetails.getUsername(), user.getEmail(), user.getUserType());
            LoginResponse loginResponse = new LoginResponse(jwt, user);
            logger.info("Login successful for email: {}, JWT: {}", user.getEmail(), jwt);
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.badRequest().body("Invalid ID Token");
        }
    }


}
