package com.demo.repository;

import com.demo.dto.ApplicationDTO;
import com.demo.entities.Application;
import com.demo.entities.Job;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {


    @Query(value = "SELECT a FROM Application a join a.job j join j.employer e where e.id = :employerId  and a.status = :status ORDER BY a.id DESC")
    public Page<Application> listApplicationByEmployerId(@Param("employerId") int employerId, Pageable pageable, @Param("status") int status);

    @Query(value = "SELECT a FROM Application a join a.job j join j.employer e where j.id = :jobId  and a.status = :status ORDER BY a.id DESC")
    public Page<Application> listApplicationByJobId(@Param("jobId") int jobId, Pageable pageable, @Param("status") int status);

    @Query(value = """
                SELECT a FROM Application a
                JOIN a.job j
                JOIN j.employer e
                WHERE e.id = :employerId AND a.status = :status
                AND a.appliedAt = (
                    SELECT MAX(a2.appliedAt) 
                    FROM Application a2 
                    WHERE a2.seeker.id = a.seeker.id 
                    AND a2.job.id = a.job.id
                )
                ORDER BY a.id DESC
            """)
    Page<Application> listDistinctApplicationByEmployerId(@Param("employerId") int employerId,
                                                          Pageable pageable,
                                                          @Param("status") int status);


    @Query(value = "SELECT COUNT(*) FROM Application a where a.job.id = :jobId and a.seeker.id = :seekerId")
    public int countApply(@Param("seekerId") int seekerId, @Param("jobId") int jobId);

    @Query("SELECT COUNT(a) FROM Application a WHERE a.job.id = :jobId")
    int countApplicantsByJobId(@Param("jobId") int jobId);


    @Query("""
                SELECT a FROM Application a
                JOIN FETCH a.job j
                WHERE a.seeker.id = :seekerId AND a.status = :status
                AND a.appliedAt = (
                    SELECT MAX(a2.appliedAt) FROM Application a2 
                    WHERE a2.seeker.id = a.seeker.id AND a2.job.id = a.job.id
                )
                ORDER BY j.id DESC
            """)
    Page<Application> listSeekerApplied(@Param("seekerId") int seekerId, Pageable pageable, @Param("status") int status);

    @Query("""
                SELECT a FROM Application a
                JOIN FETCH a.job j
                WHERE a.seeker.id = :seekerId AND a.job.id = :jobId
            
            """)
    List<Application> listSeekerAppliedForJob(@Param("seekerId") int seekerId, @Param("jobId") int jobId);


    @Query("""
                SELECT a FROM Application a 
                WHERE (:jobTitle IS NULL OR :jobTitle = '' OR LOWER(a.job.title) LIKE LOWER(CONCAT('%', :jobTitle, '%')))
                AND (:seekerName IS NULL OR :seekerName = '' OR LOWER(a.seeker.fullName) LIKE LOWER(CONCAT('%', :seekerName, '%')))
            """)
    Page<Application> searchApplication(@Param("jobTitle") String jobTitle,
                                        @Param("seekerName") String seekerName,
                                        Pageable pageable);


}
