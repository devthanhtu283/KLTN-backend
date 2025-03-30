package com.demo.controllers;

import com.demo.clients.NotificationService;
import com.demo.dtos.*;

import com.demo.entities.Chat;
import com.demo.entities.Cv;
import com.demo.entities.Seeker;
import com.demo.entities.User;
import com.demo.helpers.ApiResponseEntity;
import com.demo.helpers.FileHelper;
import com.demo.repositories.SeekerRepository;
import com.demo.services.CVService;
import com.demo.services.ChatService;
import com.demo.services.MailService;
import com.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

	@PostMapping(value = "login", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ApiResponseEntity<Object> login(@RequestBody UserDTO userDTO){
		try {
			UserDTO user = userService.findByEmail(userDTO.getEmail());
			System.out.println(user);
			boolean status = userService.login(userDTO.getEmail(), userDTO.getPassword());
			if(status) {
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
			if(result != null) {
				return ApiResponseEntity.success(result, "Successfull!!");
			} else {
				return ApiResponseEntity.error("User not found", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return ApiResponseEntity.badRequest("Error: " + e.getMessage());
		}
	}
	
	@PostMapping(value = "register", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> register(@RequestBody UserDTO userDTO){
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
	public ResponseEntity<Object> update(@RequestBody UserDTO userDTO){
		try {
			return new ResponseEntity<Object>(new Object() {
				public boolean status = userService.update(userDTO);

			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "sendEmail",  produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
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
    public ResponseEntity<Object> verifyAccount(@RequestParam String email,@RequestParam String securityCode) {
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
			if(user != null) {
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
			System.out.println( path.toString());
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
	@PostMapping(value = "cv/uploadCV", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> uploadCV(@RequestPart("file") MultipartFile file) {
		try {
			// Kiểm tra xem tệp có rỗng không
			if (file.isEmpty()) {
				return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
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
			System.out.println( path.toString());
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

	@PostMapping(value = "cv/save",  produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
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




}
