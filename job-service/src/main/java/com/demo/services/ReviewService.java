package com.demo.services;

import com.demo.dtos.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {

    Page<ReviewDTO> getAllReviewsByStatus(Boolean status, int page, int size);

    Page<ReviewDTO> getAllReviewsByEmployerId(int employerId, Boolean status, int page, int size);

    List<ReviewDTO> getAllReviewsByEmployerId(int employerId, Boolean status);

    ReviewDTO createReview(ReviewDTO reviewDTO);

    ReviewDTO updateReview(int id);

    double getApprovedReviewPercentageByEmployer(int employerId);
}
