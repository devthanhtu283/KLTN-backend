package com.demo.services;

import com.demo.dtos.ReviewDTO;
import com.demo.entities.Review;
import com.demo.repositories.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Page<ReviewDTO> getAllReviewsByStatus(Boolean status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reviewRepository.getAllReviewsByStatus(status, pageable).map(review -> modelMapper.map(review, ReviewDTO.class));
    }

    @Override
    public Page<ReviewDTO> getAllReviewsByEmployerId(int employerId, Boolean status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reviewRepository.findReviewsWithOptionalFilters(employerId, status, pageable).map(review -> modelMapper.map(review, ReviewDTO.class));
    }

    @Override
    public List<ReviewDTO> getAllReviewsByEmployerId(int employerId, Boolean status) {
        List<Review> reviews = reviewRepository.findReviewsWithOptionalFilters(employerId, status);
        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .toList();
    }


    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Review review = modelMapper.map(reviewDTO, Review.class);
        review.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        review.setStatus(false);
        Review savedReview = reviewRepository.save(review);
        return modelMapper.map(savedReview, ReviewDTO.class);
    }

    @Override
    public ReviewDTO updateReview(int id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found review"));
        review.setStatus(!review.isStatus());
        return modelMapper.map(reviewRepository.save(review), ReviewDTO.class);
    }

    @Override
    public double getApprovedReviewPercentageByEmployer(int employerId) {
        long approved = reviewRepository.countRecommendedByEmployer(employerId);
        long total = reviewRepository.countAllReviewsByEmployer(employerId);

        return total > 0 ? Math.round((approved * 100.0) / total) : 0.0;
    }
}
