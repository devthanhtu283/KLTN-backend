package com.demo.repository;

import com.demo.entities.Application;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {


    @Query(value = "SELECT a FROM Application a join a.job j join j.employer e where e.id = :employerId and a.status = :status ORDER BY a.id DESC")
    public Page<Application> listApplicationByEmployerId(@Param("employerId") int employerId, Pageable pageable,@Param("status") int status);

    @Query(value = "SELECT COUNT(*) FROM Application a where a.job.id = :jobId and a.seeker.id = :seekerId")
    public int countApply(@Param("seekerId") int seekerId, @Param("jobId") int jobId);

    @Query("""
        SELECT a FROM Application a 
        WHERE LOWER(a.job.title) LIKE LOWER(CONCAT('%', :jobTitle, '%'))
        OR LOWER(a.seeker.fullName) LIKE LOWER(CONCAT('%', :seekerName, '%'))
    """)
    Page<Application> searchApplication(@Param("jobTitle") String jobTitle,
                                        @Param("seekerName") String seekerName,
                                        Pageable pageable);

}
