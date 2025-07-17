package com.demo.repositories;

import com.demo.dtos.JobDTO;
import com.demo.entities.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface JobRepository extends CrudRepository<Job, Integer> {
    @Query("SELECT j FROM Job j " +
            "JOIN j.location l " +
            "JOIN j.worktype w " +
            "JOIN j.experience e " +
            "WHERE (:title IS NULL OR j.title LIKE %:title%) " +
            "AND (:locationId IS NULL OR l.id = :locationId) " +
            "AND (:worktypeId IS NULL OR w.id = :worktypeId) " +
            "AND (:experienceId IS NULL OR e.id = :experienceId)")
    List<Job> searchJobs(@Param("title") String title,
                         @Param("locationId") Integer locationId,
                         @Param("worktypeId") Integer worktypeId,
                         @Param("experienceId") Integer experienceId);

    @Query("SELECT j FROM Job j where j.employer.id = :employerId and j.status = :status")
    List<Job> findByEmployerId(@Param("employerId") Integer employerId, @Param("status") boolean status);

    @Query("SELECT j FROM Job j " +
            "JOIN j.location l " +
            "JOIN j.category c " +
            "WHERE (:title IS NULL OR j.title LIKE %:title%) " +
            "AND (:locationId IS NULL OR l.id = :locationId) " +
            "AND (:category IS NULL OR c.id = :categoryId) ")
    List<Job> searchBarJobs(@Param("title") String title,
                            @Param("locationId") Integer locationId,
                            @Param("categoryId") Integer categoryId);

    @Query("SELECT j FROM Job j WHERE " +
            "(:search IS NULL OR j.title LIKE %:search%)")
    Page<Job> getAllJobdAdmin(@Param("search") String search, Pageable pageable);

    long countByEmployerIdAndPostedAtBetween(Integer employerId, Date start, Date end);

    long countByEmployerId(Integer employerId);
}
