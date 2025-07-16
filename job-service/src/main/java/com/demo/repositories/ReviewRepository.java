package com.demo.repositories;

import com.demo.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("SELECT r FROM Review r WHERE r.status = :status ORDER BY r.id DESC")
    Page<Review> getAllReviewsByStatus(@Param("status") Boolean status, Pageable pageable);

    @Query("SELECT r FROM Review r " +
            "WHERE (:employerId IS NULL OR r.employer.id = :employerId) " +
            "AND (:status IS NULL OR r.status = :status) " +
            "ORDER BY r.id DESC")
    Page<Review> findReviewsWithOptionalFilters(@Param("employerId") Integer employerId,
                                                @Param("status") Boolean status,
                                                Pageable pageable);

    @Query("SELECT r FROM Review r " +
            "WHERE (:employerId IS NULL OR r.employer.id = :employerId) " +
            "AND (:status IS NULL OR r.status = :status) " +
            "ORDER BY r.id DESC")
    List<Review> findReviewsWithOptionalFilters(@Param("employerId") Integer employerId,
                                                @Param("status") Boolean status);


    @Query("SELECT COUNT(r) FROM Review r WHERE r.satisfied = true AND r.employer.id = :employerId and r.status = true")
    long countRecommendedByEmployer(@Param("employerId") int employerId);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.employer.id = :employerId and r.status = true")
    long countAllReviewsByEmployer(@Param("employerId") int employerId);

    Page<Review> findAllByStatus(Boolean status, Pageable pageable);

}
