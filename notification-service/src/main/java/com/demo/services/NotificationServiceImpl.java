package com.demo.services;

import com.demo.clients.FollowService;
import com.demo.dtos.FollowDTO;
import com.demo.entities.Employer;
import com.demo.entities.Follow;
import com.demo.entities.User;
import com.demo.helpers.ApiResponseEntity;
import com.demo.repository.EmployerRepository;
import com.demo.repository.FollowRepository;
import com.demo.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private FollowService followService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private MailService mailService;
    @Autowired
    private FollowRepository followRepository;

    @Override
    @Async
    @KafkaListener(topics = "tunh_mysql.jobs.job", groupId = "debezium-consumer-group")
    public void handleNewJobPost(ConsumerRecord<String, String> record) throws JsonProcessingException {
        System.out.println("Received Message: " + record.value());
        JsonNode root = objectMapper.readTree(record.value());

        // Kiểm tra nếu không có trường "op"
        if (!root.has("op")) {
            System.err.println("Missing 'op' field in message: " + record.value());
            return;
        }

        String operation = root.get("op").asText(); // "c" = create, "u" = update, "d" = delete

        if (operation.equals("r")) { // "r" = READ (Debezium snapshot)
            System.out.println("ℹSkipping snapshot record");
            return;
        }

        if (operation.equals("c") || operation.equals("u")) { // "c" = INSERT, "u" = UPDATE
            if (!root.has("after")) {
                System.err.println("Missing 'after' field in message: " + record.value());
                return;
            }

            JsonNode jobData = root.get("after");
            System.out.println(jobData);
            int jobId = jobData.get("id").asInt();
            String jobTitle = jobData.get("title").asText();
            int employerId = jobData.get("employer_id").asInt();

            Employer employer = employerRepository.findById(employerId);

            List<Follow> followers = followRepository.findByEmployer_IdAndStatus(employerId, true);
            System.out.println("Followers: " + followers.toString());


            for (Follow follow : followers) {
                int seekerId = follow.getSeeker().getId();
                System.out.println("🔍 Đang xử lý seekerId: " + seekerId);

                Optional<User> userOpt = userRepository.findByIdAndStatus(seekerId, 1);
                System.out.println("🔍 Kết quả findByIdAndStatus: " + userOpt);

                userOpt.ifPresent(user -> {
                    System.out.println("✅ Gửi mail đến: " + user.getEmail());
                    mailService.sendNewJobEmail(
                            user.getEmail(),
                            jobTitle,
                            employer.getCompanyName(),
                            "http://localhost:4200/seeker/job-details/" + jobId
                    );
                });
            }

        }
    }
}
