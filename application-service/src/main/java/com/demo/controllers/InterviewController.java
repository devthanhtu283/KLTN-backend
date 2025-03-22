package com.demo.controllers;

import com.demo.dto.InterviewDTO;
import com.demo.helpers.ApiResponseEntity;
import com.demo.services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("application")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @PostMapping(value = "/save-interview", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ApiResponseEntity<Object> save(@RequestBody InterviewDTO interviewDTO) {

        boolean result = interviewService.save(interviewDTO);
        return result ? ApiResponseEntity.success(true, "Successful !!")
                : ApiResponseEntity.error("No data !!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "list-interviews-of-employer")
    public ApiResponseEntity<Object> listInterviewsOfEmployer(@RequestParam("employerId") int id, Pageable pageable) {
        Page<InterviewDTO> result = interviewService.listInterviewsOfEmployer(id, pageable);

        return !result.isEmpty() ? ApiResponseEntity.success(result, "Successful !!")
                : ApiResponseEntity.error("No data !!", HttpStatus.BAD_REQUEST);
    }


    @GetMapping(value = "list-interviews-of-seeker")
    public ApiResponseEntity<Object> listInterviewsOfSeeker(@RequestParam("seekerId") int id, Pageable pageable) {
        Page<InterviewDTO> result = interviewService.listInterviewsOfSeeker(id, pageable);

        return !result.isEmpty() ? ApiResponseEntity.success(result, "Successful !!")
                : ApiResponseEntity.error("No data !!", HttpStatus.BAD_REQUEST);
    }


    @PutMapping(value = "update-interview")
    public ApiResponseEntity<Object> update(@RequestBody InterviewDTO interviewDTO) {
        InterviewDTO result = interviewService.update(interviewDTO);

        return result != null ? ApiResponseEntity.success(result, "Updated Successful !!")
                : ApiResponseEntity.error("Updated Failed !!", HttpStatus.BAD_REQUEST);
    }
}
