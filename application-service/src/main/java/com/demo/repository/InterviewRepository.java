package com.demo.repository;

import com.demo.entities.Interview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {

    @Query("SELECT i FROM Interview i " +
            "JOIN i.application a " +
            "JOIN a.job j " +
            "WHERE j.employer.id = :employeeId and i.status != 0 " +
            "ORDER BY i.scheduledAt DESC")
    public Page<Interview> listInterviewsOfEmployer(@Param("employeeId") int employeeId, Pageable pageable);

    @Query("SELECT i FROM Interview i " +
            "JOIN i.application a " +
            "WHERE a.seeker.id = :seekerId " +
            "ORDER BY i.scheduledAt DESC")
    public Page<Interview> listInterviewsOfSeeker(@Param("seekerId") int seekerId, Pageable pageable);


}
