package com.demo.repository;

import com.demo.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    Optional<Job> findJobById(int jobId);
}
