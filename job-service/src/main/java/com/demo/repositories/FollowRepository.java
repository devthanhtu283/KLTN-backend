package com.demo.repositories;

import com.demo.entities.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {

    Optional<Follow> findByEmployer_IdAndSeeker_Id(int employerId, int SeekerId);
}
