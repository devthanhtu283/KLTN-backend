package com.demo.repository;

import com.demo.entities.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CVRepository extends JpaRepository<Cv, Integer> {

    Optional<Cv> findById(Integer cvId);

    List<Cv> findByStatus(boolean status);
}
