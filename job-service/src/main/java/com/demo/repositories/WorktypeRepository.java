package com.demo.repositories;

import com.demo.entities.Job;
import com.demo.entities.Worktype;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorktypeRepository extends CrudRepository<Worktype, Integer> {
}
