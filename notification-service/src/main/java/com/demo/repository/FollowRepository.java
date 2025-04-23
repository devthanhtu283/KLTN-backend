package com.demo.repository;

import com.demo.entities.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {

    Optional<Follow> findByEmployer_IdAndSeeker_Id(int employerId, int SeekerId);

    List<Follow> findByEmployer_IdAndStatus(int employerId, boolean status);
}
