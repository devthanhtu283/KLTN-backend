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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("user")
public class SeekerController {

	@Autowired
	private SeekerService seekerService;

	@PostMapping(value = "seeker/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponseEntity<SeekerDTO> update(@RequestParam("seekerDTO") String seekerDTO,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		try {
			String photo = null;

			// Chỉ gọi upload nếu file không null
			if (file != null) {
				photo = upload(file);

				// Cắt bỏ phần "/uploads/" từ đường dẫn hình ảnh
				if (photo != null && photo.startsWith("/uploads/")) {
					photo = photo.substring("/uploads/".length());
				}
			}

			Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateHelper()).create();
			SeekerDTO seeker = gson.fromJson(seekerDTO, SeekerDTO.class);
			seeker.setAvatar(photo);
			seeker.setUpdateAt(Timestamp.from(Instant.now()));

			boolean status = seekerService.update(seeker);
			if (status) {
				// Trả về phản hồi thành công
				return ApiResponseEntity.success(seeker, "Seeker saved successfully!");
			} else {
				// Trả về phản hồi thất bại nếu lưu không thành công
				return ApiResponseEntity.success(seeker,"Failed to save seeker.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponseEntity.badRequest("Error " + e.getMessage());
		}
	}

	@GetMapping(value = "seeker/findById/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ApiResponseEntity<Object> findById(@PathVariable("id") int id) {
		try {
			SeekerDTO seekerDTO = seekerService.findById(id);
			System.out.println(seekerDTO);
			if(seekerDTO != null) {
				return ApiResponseEntity.success(seekerDTO, "Seeker found");
			} else {
				return ApiResponseEntity.error("Seeker not found", HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponseEntity.badRequest("Error " + e.getMessage());
		}
	}

	@PostMapping(value = "seeker/upload", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public String upload(@RequestPart("file") MultipartFile file) {
		try {
			// Kiểm tra xem tệp có rỗng không
			if (file.isEmpty()) {
				return null;
			}

			// Lấy thông tin của tệp
			String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
			String contentType = file.getContentType();
			long size = file.getSize();

			// Thư mục lưu trữ tệp (bên ngoài classpath)
			String uploadDir = "uploads";
			File uploadFolder = new File(uploadDir);
			if (!uploadFolder.exists()) {
				uploadFolder.mkdirs();
			}

			// Tạo tên tệp duy nhất
			String filename = FileHelper.generateFileName(originalFilename); // hoặc sử dụng phương thức
																				// generateFileName

			// Tạo đường dẫn lưu trữ tệp
			Path path = Paths.get(uploadFolder.getAbsolutePath() + File.separator + filename);

			// Lưu tệp vào thư mục
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

			// Tạo URL cho tệp đã tải lên
			String fileUrl = "/uploads/" + filename;
			return fileUrl;
		} catch (IOException e) {
			e.printStackTrace();
			return "Lỗi khi tải lên tệp: " + e.getMessage();
		}

	}
}
