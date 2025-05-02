package com.demo.controllers;

import com.demo.clients.NotificationService;
import com.demo.dtos.*;

import com.demo.entities.*;
import com.demo.helpers.ApiResponseEntity;
import com.demo.helpers.FileHelper;
import com.demo.helpers.PaymentProcessor;
import com.demo.repositories.SeekerRepository;
import com.demo.services.*;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private CVService cvService;
    @Autowired
    private SeekerRepository seekerRepository;
    @Autowired
    private EmployerMembershipService employerMembershipService;
    @Autowired
    private PaymentService paymentService;
    @Value("${spring.profiles.active:dev}")
    private String activeProfile;

    @PostMapping(value = "login", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ApiResponseEntity<Object> login(@RequestBody UserDTO userDTO) {
        try {
            UserDTO user = userService.findByEmail(userDTO.getEmail());
            System.out.println(user);
            boolean status = userService.login(userDTO.getEmail(), userDTO.getPassword());
            if (status) {
                return ApiResponseEntity.success(user, "Successfull!!");
            } else {
                return ApiResponseEntity.error("Failure !!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponseEntity.badRequest("Error " + e.getMessage());
        }
    }

    @GetMapping(value = "findById/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ApiResponseEntity<Object> findById(@PathVariable int id) {
        try {
            UserDTO result = userService.findById(id);
            if (result != null) {
                return ApiResponseEntity.success(result, "Successfull!!");
            } else {
                return ApiResponseEntity.error("User not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return ApiResponseEntity.badRequest("Error: " + e.getMessage());
        }
    }

    @PostMapping(value = "register", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody UserDTO userDTO) {
        try {
            return new ResponseEntity<Object>(new Object() {
                public boolean status = userService.save(userDTO);

            }, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "update", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@RequestBody UserDTO userDTO) {
        try {
            return new ResponseEntity<Object>(new Object() {
                public boolean status = userService.update(userDTO);

            }, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "sendEmail", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)

    public ResponseEntity<Object> sendEmail(@RequestBody Email email) {
        try {
            return new ResponseEntity<Object>(new Object() {
                public boolean status = notificationService.sendEmail(email).hasBody();
            }, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "verifyAccount")
    public ResponseEntity<Object> verifyAccount(@RequestParam String email, @RequestParam String securityCode) {
        try {
            return new ResponseEntity<Object>(new Object() {
                public boolean status = userService.verify(email, securityCode);
            }, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "findByEmail/{email}")
    public ApiResponseEntity<Object> findByEmail(@PathVariable String email) {
        try {
            UserDTO user = userService.findByEmail(email);
            if (user != null) {
                return ApiResponseEntity.success(user, "Successfull!!");
            } else {
                return ApiResponseEntity.error("Email not found", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ApiResponseEntity.badRequest("Error " + e.getMessage());
        }
    }

    @PostMapping(value = "upload", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestPart("file") MultipartFile file, @RequestPart("seekerDTO") String seekerDTO) {
        try {
            System.out.println(seekerDTO);
            // Kiểm tra xem tệp có rỗng không
            if (file.isEmpty()) {
                return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
            }


            // Lấy thông tin của tệp
            String originalFilename = file.getOriginalFilename();
            String contentType = file.getContentType();
            long size = file.getSize();

            // Thư mục lưu trữ tệp
            File uploadFolder = new File(new ClassPathResource("static/assets/images").getFile().getAbsolutePath());
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            // Tạo tên tệp duy nhất
            String filename = FileHelper.generateFileName(originalFilename); // hoặc sử dụng phương thức generateFileName

            // Tạo đường dẫn lưu trữ tệp
            Path path = Paths.get(uploadFolder.getAbsolutePath() + File.separator + filename);
            System.out.println(path.toString());
            // Lưu tệp vào thư mục
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // Tạo URL cho tệp đã tải lên
            String fileUrl = filename;
            // Trả về URL của tệp đã tải lên
            System.out.println(fileUrl);
            return ResponseEntity.ok().body(new Object() {
                public String url = fileUrl;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }


    @PostMapping("chat/save-message")
    public ApiResponseEntity<Object> saveMessage(@RequestBody ChatDTO chatMessage) {
        ChatDTO savedMessage = chatService.save(chatMessage);

        return savedMessage != null
                ? ApiResponseEntity.success(savedMessage, "Saved Message Successfully")
                : ApiResponseEntity.success(savedMessage, "Failed to save message");

    }


    @PostMapping(value = "uploadCV", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> uploadCV(@RequestPart("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
            }

            String originalFilename = file.getOriginalFilename();
            String filename = FileHelper.generateFileName(originalFilename);

            String uploadDir;

            // ✅ Chia 2 trường hợp dựa trên profile
            if ("docker".equals(activeProfile)) {
                uploadDir = "/app/user-static/assets/files"; // Docker path
            } else {
                // Dev: lưu vào classpath/static (chỉ dùng được khi chạy bằng IDE)
                uploadDir = new ClassPathResource("static/assets/files").getFile().getAbsolutePath();
            }

            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            Path path = Paths.get(uploadDir + File.separator + filename);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            String fileUrl = "/assets/files/" + filename;

            return ResponseEntity.ok().body(new Object() {
                public String url = fileUrl;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }

    @GetMapping("chat/getReceiverIdsByUserId/{userId}")
    public ResponseEntity<List<Integer>> getReceiverIdsByUserId(@PathVariable Integer userId) {
        try {
            List<Integer> receiverIds = chatService.getReceiverIdsByUserId(userId);
            if (receiverIds.isEmpty()) {
                return ResponseEntity.noContent().build(); // Trả về 204 nếu không có dữ liệu
            }
            return ResponseEntity.ok(receiverIds); // Trả về 200 với danh sách receiver_id
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Trả về 500 nếu có lỗi
        }
    }

    @GetMapping("/chat/getMessagesBetweenUsers/{senderId}/{receiverId}")
    public ResponseEntity<List<ChatDTO>> getMessagesBetweenUsers(
            @PathVariable Integer senderId,
            @PathVariable Integer receiverId) {
        try {
            List<ChatDTO> messages = chatService.getMessagesBetweenUsers(senderId, receiverId);
            if (messages.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }


    @PostMapping(value = "cv/save", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveCV(@RequestBody CvDTO cv) {
        try {
            return new ResponseEntity<Object>(new Object() {
                public boolean status = cvService.save(cv);
            }, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "cv/findBySeekerId/{id}")
    public ResponseEntity<Object> findCVBySeekerId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<Object>(cvService.getCvById(id), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "payment/{amount}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveCV(@PathVariable long amount) {
        try {
            return new ResponseEntity<Object>(new Object() {
                public String paymentUrl = PaymentProcessor.processPayment(amount);
            }, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "employerMembership/create", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createEmployerMembership(@RequestBody EmployerMembershipDTO employerMembershipDTO) {
        try {
            Employermembership employermembership = employerMembershipService.create(employerMembershipDTO);
            return new ResponseEntity<Object>(new Object() {
                public boolean status = employermembership != null;
                public Employermembership object = employermembership;
            }, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "employerMembership/findByUserId/{userId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findEmployerMembershipByUserId(@PathVariable("userId") int userId) {
        try {
            return new ResponseEntity<Object>(employerMembershipService.findByUserId(userId), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "payment/create", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createPayment(@RequestBody PaymentDTO paymentDTO) {
        try {
            return new ResponseEntity<Object>(new Object() {
                public boolean status = paymentService.create(paymentDTO);
            }, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "applyCV", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ApiResponseEntity<Object> applyCV(@RequestPart("file") MultipartFile file) {
        try {
            // Kiểm tra xem tệp có rỗng không
            if (file.isEmpty()) {
                return ApiResponseEntity.badRequest("file is empty");
            }
            // Lấy thông tin của tệp
            String originalFilename = file.getOriginalFilename();
            String contentType = file.getContentType();
            long size = file.getSize();

            // Thư mục lưu trữ tệp
            File uploadFolder = new File(new ClassPathResource("static/assets/files").getFile().getAbsolutePath());
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            // Tạo tên tệp duy nhất
            String filename = FileHelper.generateFileName(originalFilename); // hoặc sử dụng phương thức generateFileName

            // Tạo đường dẫn lưu trữ tệp
            Path path = Paths.get(uploadFolder.getAbsolutePath() + File.separator + filename);
            System.out.println(path.toString());
            // Lưu tệp vào thư mục
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // Tạo URL cho tệp đã tải lên
            String fileUrl = "/assets/files/" + filename;
            String fullFileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(fileUrl)
                    .toUriString();

            return ApiResponseEntity.success(fullFileUrl, "Successfull!!");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponseEntity.badRequest("Error uploading file");
        }
    }

    @PostMapping(value = "sendEmailTest", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sendEmailTest(@RequestBody Email email) {
        try {
            return new ResponseEntity<Object>(new Object() {
                public boolean status = mailService.send(email.getFrom(), email.getTo(), email.getSubject(), email.getContent());
            }, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

}
