package com.demo.services;

import com.demo.clients.FollowService;
import com.demo.entities.Employer;
import com.demo.entities.Follow;
import com.demo.entities.User;
import com.demo.repository.EmployerRepository;
import com.demo.repository.FollowRepository;
import com.demo.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
public class NotificationServiceImpl implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
    private static final int BATCH_SIZE = 100;

    @Autowired
    private FollowService followService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private FollowRepository followRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Async
    @KafkaListener(topics = "tunh_mysql.jobs.job", groupId = "debezium-consumer-group")
    public void handleNewJobPost(ConsumerRecord<String, String> record) throws JsonProcessingException {
        logger.info("Received Message: {}", record.value());
        JsonNode root = objectMapper.readTree(record.value());

        if (!root.has("op")) {
            logger.error("Missing 'op' field in message: {}", record.value());
            return;
        }

        String operation = root.get("op").asText();

        if (operation.equals("r")) {
            logger.info("ℹSkipping snapshot record");
            return;
        }

        if (operation.equals("c") || operation.equals("u")) {
            processJobUpdate(root);
        }
    }

    private void processJobUpdate(JsonNode root) {
        if (!root.has("after")) {
            logger.error("Missing 'after' field in message");
            return;
        }

        JsonNode jobData = root.get("after");
        int jobId = jobData.get("id").asInt();
        String jobTitle = jobData.get("title").asText();
        int employerId = jobData.get("employer_id").asInt();

        Employer employer = employerRepository.findById(employerId);
        List<Follow> followers = followRepository.findByEmployer_IdAndStatus(employerId, true);

        if (followers.isEmpty()) {
            logger.info("No followers found for employer: {}", employerId);
            return;
        }

        // Process followers in batches
        List<List<Follow>> batches = getBatches(followers, BATCH_SIZE);
        String jobUrl = "http://localhost:4200/seeker/job-details/" + jobId;

        batches.forEach(batch ->
                processBatch(batch, jobTitle, employer.getCompanyName(), jobUrl)
        );
    }

    private void processBatch(List<Follow> batch, String jobTitle, String companyName, String jobUrl) {
        List<CompletableFuture<Void>> futures = batch.stream()
                .map(follow -> follow.getSeeker().getId())
                .map(seekerId -> userRepository.findByIdAndStatus(seekerId, 1))
                .filter(userOpt -> userOpt.isPresent())
                .map(userOpt -> userOpt.get())
                .map(user -> sendEmailAsync(user, jobTitle, companyName, jobUrl))
                .collect(Collectors.toList());

        // Wait for all emails in batch to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .exceptionally(throwable -> {
                    logger.error("Error processing email batch", throwable);
                    return null;
                });
    }

    @Async
    protected CompletableFuture<Void> sendEmailAsync(User user, String jobTitle, String companyName, String jobUrl) {
        return CompletableFuture.runAsync(() -> {
            try {
                logger.info("Sending email to: {}", user.getEmail());
                mailService.sendNewJobEmail(
                        user.getEmail(),
                        jobTitle,
                        companyName,
                        jobUrl
                );
            } catch (Exception e) {
                logger.error("Failed to send email to: {}", user.getEmail(), e);
            }
        });
    }

    private <T> List<List<T>> getBatches(List<T> collection, int batchSize) {
        return IntStream.range(0, (collection.size() + batchSize - 1) / batchSize)
                .mapToObj(i -> collection.subList(
                        i * batchSize,
                        Math.min((i + 1) * batchSize, collection.size())
                ))
                .collect(Collectors.toList());
    }
}