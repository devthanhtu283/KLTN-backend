package com.demo.repositories;

import com.demo.entities.Follow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {

    Optional<Follow> findByEmployer_IdAndSeeker_Id(int employerId, int SeekerId);

    List<Follow> findByEmployer_IdAndStatus(int employerId, boolean status);

    Page<Follow> findBySeeker_IdAndStatus(int seekerId, boolean status, Pageable pageable);
}
