package com.demo.repositories;

import com.demo.entities.Question;
import com.demo.entities.Testhistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TestHistoryRepository extends CrudRepository<Testhistory, Integer> {

}
