package com.demo.repositories;

import com.demo.entities.Job;
import com.demo.entities.Matches;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchesRepository extends JpaRepository<Matches, Long> {

    @Query("SELECT m FROM Matches m JOIN m.cv c JOIN c.seeker s WHERE s.id = :seekerId ORDER BY m.id DESC")
    Page<Matches> getRecommendJobsBySeekerId(@Param("seekerId") Integer seekerId, Pageable pageable);

    @Query("SELECT m FROM Matches m " +
            "JOIN m.job j " +
            "JOIN m.cv c " +
            "JOIN c.seeker s " +
            "JOIN j.location l " +
            "JOIN j.worktype w " +
            "JOIN j.experience e " +
            "JOIN j.category c2 " +
            "WHERE s.id = :seekerId " +
            "AND (:title IS NULL OR j.title LIKE %:title%) " +
            "AND (:locationId IS NULL OR l.id = :locationId) " +
            "AND (:worktypeId IS NULL OR w.id = :worktypeId) " +
            "AND (:categoryId IS NULL OR c2.id = :categoryId) " +
            "AND (:experienceId IS NULL OR e.id = :experienceId)")
    Page<Matches> searchRecommendJobs(@Param("seekerId") Integer seekerId,
                                      @Param("title") String title,
                                      @Param("locationId") Integer locationId,
                                      @Param("worktypeId") Integer worktypeId,
                                      @Param("experienceId") Integer experienceId,
                                      @Param("categoryId") Integer categoryId,
                                      Pageable pageable);
}
