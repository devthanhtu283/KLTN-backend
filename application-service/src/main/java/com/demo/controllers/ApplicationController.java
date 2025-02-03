package com.demo.controllers;

import com.demo.dto.ApplicationDTO;
import com.demo.dto.ApplicationIndex;
import com.demo.entities.Application;
import com.demo.helpers.ApiResponseEntity;
import com.demo.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping(value = "findAll", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ApiResponseEntity<Object> listApplications() {
        try {
            List<ApplicationDTO> result = applicationService.listApplications();
            if(result != null) {
                return ApiResponseEntity.success(result, "Successful !!");
            } else {
                return ApiResponseEntity.error("No data !!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return  ApiResponseEntity.badRequest("Error " + e.getMessage());
        }
    }

    @GetMapping(value = "findById/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ApiResponseEntity<Object> findById(@PathVariable int id) {
        try {
            ApplicationDTO result = applicationService.findById(id);
            if(result != null) {
                return ApiResponseEntity.success(result, "Successful !!");
            } else {
                return ApiResponseEntity.error("No data found!!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return ApiResponseEntity.badRequest("Error " + e.getMessage());
        }
    }


    @PostMapping(value = "save", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ApiResponseEntity<Object> save(@RequestBody ApplicationDTO applicationDTO) {
        try {
            boolean result = applicationService.save(applicationDTO);
            if(result) {
                return ApiResponseEntity.success(result, "Successful !!");
            } else {
                return ApiResponseEntity.error("No data !!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return ApiResponseEntity.badRequest("Error " + e.getMessage());
        }
    }

    @GetMapping(value = "list-seeker", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ApiResponseEntity<Object> listApplicationByEmployerId(@RequestParam int employerId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam int status) {
        try {
            Page<ApplicationDTO> result = applicationService.listApplicationByEmployerId(employerId, page, size, status);
            if(result != null) {
                return ApiResponseEntity.success(result, "Successful !!");
            } else {
                return ApiResponseEntity.error("No data !!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return ApiResponseEntity.badRequest("Error " + e.getMessage());
        }
    }

    @PutMapping(value = "update", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ApiResponseEntity<Object> update(@RequestBody ApplicationDTO applicationDTO) {
        try {
            if(applicationDTO != null) {
                ApplicationDTO result = applicationService.updateStatus(applicationDTO.getId(), applicationDTO.getStatus());

                if(result != null) {
                    return ApiResponseEntity.success(result, "Successful !!");
                } else {
                    return ApiResponseEntity.error("Failed !!", HttpStatus.BAD_REQUEST);
                }
            } else {
                return ApiResponseEntity.error("Application not found !!", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping(value = "search", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ApiResponseEntity<Object> searchApplication(@RequestParam(required = false) String jobTitle, @RequestParam(required = false) String seekerName, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        try {
            System.out.println("🔍 API nhận request: jobTitle=" + jobTitle + ", seekerName=" + seekerName);
            Page<ApplicationIndex> result = applicationService.searchApplication(jobTitle, seekerName, page, size);
            System.out.println("🔍 API trả về: " + result.getTotalElements() + " kết quả");
            if(result != null) {
                return ApiResponseEntity.success(result, "Successful !!");
            } else {
                return ApiResponseEntity.error("No data !!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return ApiResponseEntity.badRequest("Error " + e.getMessage());
        }
    }

    // API để đồng bộ dữ liệu từ database lên Elasticsearch
    @PostMapping("/sync")
    public ResponseEntity<String> syncDataToElasticsearch() {
        applicationService.saveDBIntoElasticsearch();
        return ResponseEntity.ok("Dữ liệu đã được đồng bộ lên Elasticsearch thành công!");
    }
}
