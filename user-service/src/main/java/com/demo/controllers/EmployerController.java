package com.demo.controllers;

import com.demo.helpers.ApiResponseEntity;
import com.demo.helpers.FileHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;

import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.*;

import com.demo.dtos.EmployerDTO;
import com.demo.dtos.SeekerDTO;
import com.demo.services.EmployerService;
import com.demo.services.SeekerService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("user")
public class EmployerController {

	
	@Autowired
	private EmployerService employerService;

	@Autowired
	private ObjectMapper objectMapper;
	@PostMapping(value = "employer/save", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> save(@RequestBody EmployerDTO employerDTO){
		try {
			return new ResponseEntity<Object>(new Object() {
				public boolean status = employerService.save(employerDTO);
				
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "employer/findById/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ApiResponseEntity<Object> findById(@PathVariable("id") int id){
		try {
			EmployerDTO employerDTO = employerService.findById(id);
			System.out.println(employerDTO);
			if(employerDTO != null) {
				return ApiResponseEntity.success(employerDTO, "Employer found");
			} else {
				return ApiResponseEntity.error("Employer not found", HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ApiResponseEntity.badRequest("Error " + e.getMessage());
		}
	}

	@PostMapping(value = "employer/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> uploadAndUpdateProfile(
			@RequestPart(value = "logo", required = false) MultipartFile logo,
			@RequestPart(value = "coverImg", required = false) MultipartFile coverImg,
			@RequestPart("employerDTO") String employerDTOStr) {

		try {
			// Parse JSON to EmployerDTO
			EmployerDTO employerDTO = objectMapper.readValue(employerDTOStr, EmployerDTO.class);
			String logoUrl = null;
			String coverImgUrl = null;

			// Get current logo and cover image from existing employer
			EmployerDTO currentEmployer = employerService.findById(employerDTO.getId());
			String currentLogo = currentEmployer != null ? currentEmployer.getLogo() : null;
			String currentCoverImg = currentEmployer != null ? currentEmployer.getCoverImg() : null;

			// Create upload directory
			Resource resource = new ClassPathResource("static/assets/images");
			File uploadFolder = resource.getFile();
			if (!uploadFolder.exists()) {
				uploadFolder.mkdirs();
			}

			// Handle logo upload if provided
			if (logo != null && !logo.isEmpty()) {
				// Validate logo file
				String originalLogoFilename = StringUtils.cleanPath(logo.getOriginalFilename());
				String logoContentType = logo.getContentType();
				if (!logoContentType.startsWith("image/")) {
					return ResponseEntity.badRequest().body("Logo must be an image file");
				}

				// Generate unique filename
				String logoFilename = FileHelper.generateFileName(originalLogoFilename);
				Path logoPath = Paths.get(uploadFolder.getAbsolutePath() + File.separator + logoFilename);
				Files.copy(logo.getInputStream(), logoPath, StandardCopyOption.REPLACE_EXISTING);
				logoUrl = logoFilename;
				employerDTO.setLogo(logoUrl);
			} else {
				employerDTO.setLogo(currentLogo);
			}

			// Handle cover image upload if provided
			if (coverImg != null && !coverImg.isEmpty()) {
				// Validate cover image file
				String originalCoverFilename = StringUtils.cleanPath(coverImg.getOriginalFilename());
				String coverContentType = coverImg.getContentType();
				if (!coverContentType.startsWith("image/")) {
					return ResponseEntity.badRequest().body("Cover image must be an image file");
				}

				// Generate unique filename
				String coverFilename = FileHelper.generateFileName(originalCoverFilename);
				Path coverPath = Paths.get(uploadFolder.getAbsolutePath() + File.separator + coverFilename);
				Files.copy(coverImg.getInputStream(), coverPath, StandardCopyOption.REPLACE_EXISTING);
				coverImgUrl = coverFilename;
				employerDTO.setCoverImg(coverImgUrl);
			} else {
				employerDTO.setCoverImg(currentCoverImg);
			}

			// Update employer information
			boolean status = employerService.save(employerDTO);
			EmployerDTO updatedEmployer = null;
			if (status) {
				updatedEmployer = employerService.findById(employerDTO.getId());
			}

			// Create response
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Profile updated successfully");
			response.put("data", updatedEmployer);
			if (logoUrl != null) {
				response.put("logoUrl", logoUrl);
			}
			if (coverImgUrl != null) {
				response.put("coverImgUrl", coverImgUrl);
			}

			return ResponseEntity.ok(response);

		} catch (IOException e) {
			return ResponseEntity.badRequest().body(Collections.singletonMap("error", "File upload failed: " + e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(Collections.singletonMap("error", e.getMessage()));
		}
	}



    @GetMapping(value = "employer/get-large-companies")
    public ApiResponseEntity<Object> getLargeCompanies(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<EmployerDTO> res = employerService.getLargeCompanies(page, size);
        return !res.isEmpty() ? ApiResponseEntity.success(res, "Data found successfully !!")
                : ApiResponseEntity.badRequest("No data found");
    }

    @GetMapping(value = "employer/get-medium-companies")
    public ApiResponseEntity<Object> getMediumAndSmallCompanies(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<EmployerDTO> res = employerService.getMediumAndSmallCompanies(page, size);
        return !res.isEmpty() ? ApiResponseEntity.success(res, "Data found successfully !!")
                : ApiResponseEntity.badRequest("No data found");
    }

    @GetMapping("employer/search")
    public ApiResponseEntity<Object> search(@RequestParam String keyword) {
        List<EmployerDTO> res = employerService.searchByKeyword(keyword);

        return !res.isEmpty() ? ApiResponseEntity.success(res, "Data found successfully !!")
                : ApiResponseEntity.badRequest("No data found");
    }

}
