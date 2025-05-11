package com.demo.repositories;

import com.demo.entities.Favorite;
import com.demo.entities.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPaginationRepository extends PagingAndSortingRepository<Job, Integer> {
    @Query("SELECT j FROM Job j " +
            "JOIN j.location l " +
            "JOIN j.worktype w " +
            "JOIN j.experience e " +
            "JOIN j.category c " +
            "WHERE (:title IS NULL OR j.title LIKE %:title%) " +
            "AND (:locationId IS NULL OR l.id = :locationId) " +
            "AND (:worktypeId IS NULL OR w.id = :worktypeId) " +
            "AND (:categoryId IS NULL OR c.id = :categoryId) " +
            "AND (:experienceId IS NULL OR e.id = :experienceId)")
    Page<Job> searchJobs(@Param("title") String title,
                         @Param("locationId") Integer locationId,
                         @Param("worktypeId") Integer worktypeId,
                         @Param("experienceId") Integer experienceId,
                         @Param("categoryId") Integer categoryId,
                         Pageable pageable);

    @Query("SELECT j FROM Job j " +
            "JOIN j.location l " +
            "JOIN j.category c " +
            "WHERE (:title IS NULL OR j.title LIKE %:title%) " +
            "AND (:locationId IS NULL OR l.id = :locationId) " +
            "AND (:categoryId IS NULL OR c.id = :categoryId) ")
    Page<Job> searchBarJobs(@Param("title") String title,
                            @Param("locationId") Integer locationId,
                            @Param("categoryId") Integer categoryId,
                            Pageable pageable);

    Page<Job> findByEmployerId(@Param("employerId") Integer employerId, Pageable pageable);

    Page<Job> findByEmployerIdAndTitleContainingIgnoreCase(int employerId, String title, Pageable pageable);

}
