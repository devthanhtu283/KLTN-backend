package com.demo.controllers;

import com.demo.dtos.ReviewDTO;
import com.demo.entities.Review;
import com.demo.helpers.ApiResponse;
import com.demo.helpers.ApiResponseEntity;
import com.demo.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("job")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public ApiResponseEntity<Object> getAllReviews(@RequestParam(required = false) Integer employerId,
                                                   @RequestParam(required = false) Boolean status,
                                                   @RequestParam(required = false) Integer page,
                                                   @RequestParam(required = false, defaultValue = "10") Integer size) {

        if (employerId != null && page != null) {
            Page<ReviewDTO> result = reviewService.getAllReviewsByEmployerId(employerId, status, page, size);
            return result.hasContent()
                    ? ApiResponseEntity.success(result, "Successful !!")
                    : ApiResponseEntity.success(result, "No data !!");

        } else if (employerId != null) {
            List<ReviewDTO> res = reviewService.getAllReviewsByEmployerId(employerId, status);
            return !res.isEmpty()
                    ? ApiResponseEntity.success(res, "Successful !!")
                    : ApiResponseEntity.success(res, "No data !!");

        } else {
            Page<ReviewDTO> result = reviewService.getAllReviewsByStatus(status, page != null ? page : 0, size != null ? size : 10);
            return result.hasContent()
                    ? ApiResponseEntity.success(result, "Successful !!")
                    : ApiResponseEntity.success(result, "No data !!");
        }
    }

    @GetMapping("/reviews/approved-percent")
    public ApiResponseEntity<Object> getApprovedReviewPercentByEmployer(@RequestParam("employerId") int employerId) {
        double percent = reviewService.getApprovedReviewPercentageByEmployer(employerId);
        return ApiResponseEntity.success(percent, "Successfully calculated approved percentage.");
    }


    @PostMapping("/reviews")
    public ApiResponseEntity<Object> createReview(@RequestBody ReviewDTO reviewDTO) {
        ReviewDTO res = reviewService.createReview(reviewDTO);
        return res != null ? ApiResponseEntity.success(res, "Successfull")
                : ApiResponseEntity.badRequest("Error creating review");
    }

    @PutMapping("/reviews/{id}")
    public ApiResponseEntity<Object> updateReview(@PathVariable int id) {
        ReviewDTO res = reviewService.updateReview(id);
        return res != null ? ApiResponseEntity.success(res, "Successfull")
                : ApiResponseEntity.badRequest("Error creating review");
    }

}
