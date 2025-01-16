package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.dtos.QuestionDTO;
import com.demo.entities.Question;
import com.demo.entities.Test;
import com.demo.entities.User;

public interface QuestionRepository extends CrudRepository<Question, Integer>{
	@Query("from Question where test.id = :id")
	public List<Question> findQuestionByTestID(@Param("id") int id);
}
