package com.demo.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonSyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.dtos.SeekerDTO;
import com.demo.entities.Seeker;
import com.demo.helpers.ApiResponse;
import com.demo.helpers.ApiResponseEntity;
import com.demo.helpers.DateHelper;
import com.demo.helpers.FileHelper;
import com.demo.services.SeekerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("user")
public class SeekerController {

    @Autowired
    private SeekerService seekerService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = "seeker/findById/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ApiResponseEntity<Object> findById(@PathVariable("id") int id) {
        try {
            SeekerDTO seekerDTO = seekerService.findById(id);
            System.out.println(seekerDTO);
            if (seekerDTO != null) {
                return ApiResponseEntity.success(seekerDTO, "Seeker found");
            } else {
                return ApiResponseEntity.error("Seeker not found", HttpStatus.OK);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponseEntity.badRequest("Error " + e.getMessage());
        }
    }

    //	@PostMapping(value = "seeker/upload", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
//	public String upload(@RequestPart("file") MultipartFile file) {
//		try {
//			// Kiểm tra xem tệp có rỗng không
//			if (file.isEmpty()) {
//				return null;
//			}
//
//			// Lấy thông tin của tệp
//			String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
//			String contentType = file.getContentType();
//			long size = file.getSize();
//
//			// Thư mục lưu trữ tệp (bên ngoài classpath)
//			String uploadDir = "uploads";
//			File uploadFolder = new File(uploadDir);
//			if (!uploadFolder.exists()) {
//				uploadFolder.mkdirs();
//			}
//
//			// Tạo tên tệp duy nhất
//			String filename = FileHelper.generateFileName(originalFilename); // hoặc sử dụng phương thức
//																				// generateFileName
//
//			// Tạo đường dẫn lưu trữ tệp
//			Path path = Paths.get(uploadFolder.getAbsolutePath() + File.separator + filename);
//
//			// Lưu tệp vào thư mục
//			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//
//			// Tạo URL cho tệp đã tải lên
//			String fileUrl = "/uploads/" + filename;
//			return fileUrl;
//		} catch (IOException e) {
//			e.printStackTrace();
//			return "Lỗi khi tải lên tệp: " + e.getMessage();
//		}
//
//	}
    @PostMapping(value = "seeker/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> uploadAndUpdateProfile(
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestPart("seekerDTO") String seekerDTOStr) {

        try {
            // Parse JSON sang đối tượng DTO
            SeekerDTO seekerDTO = objectMapper.readValue(seekerDTOStr, SeekerDTO.class);
            String fileUrl = null;
            String currentAvatar = seekerService.findById(seekerDTO.getId()).getAvatar();
            System.out.println("Current ava: " + currentAvatar);
            // Xử lý upload file nếu có
            if (file != null && !file.isEmpty()) {
                // Validate file
                String originalFilename = file.getOriginalFilename();
                String contentType = file.getContentType();

                // Kiểm tra loại file (ví dụ: chỉ cho phép ảnh)
                if (!contentType.startsWith("image/")) {
                    return ResponseEntity.badRequest().body("Only image files are allowed");
                }

                // Tạo thư mục lưu trữ
                Resource resource = new ClassPathResource("static/assets/images");
                File uploadFolder = resource.getFile();
                if (!uploadFolder.exists()) {
                    uploadFolder.mkdirs();
                }

                // Tạo tên file độc nhất
                String filename = FileHelper.generateFileName(originalFilename);

                // Lưu file
                Path path = Paths.get(uploadFolder.getAbsolutePath() + File.separator + filename);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                fileUrl = filename;
                seekerDTO.setAvatar(fileUrl); // Cập nhật avatar vào DTO
            } else {
                seekerDTO.setAvatar(currentAvatar);
            }

            // Cập nhật thông tin người dùng
            boolean status = seekerService.save(seekerDTO);
            seekerDTO.setUpdateAt(new Timestamp(Instant.now().toEpochMilli()));
            SeekerDTO updatedSeeker = null;
            if (status) {
                updatedSeeker = seekerService.findById(seekerDTO.getId());
            }

            // Tạo response
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Profile updated successfully");
            response.put("data", updatedSeeker);
            if (fileUrl != null) {
                response.put("avatarUrl", fileUrl);
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PostMapping(value = "seeker/applyCV", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
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

}
