package com.demo.repositories;

import com.demo.entities.Experience;
import com.demo.entities.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends CrudRepository<Experience, Integer> {
}
