package com.demo.repositories;

import java.util.List;

import com.demo.entities.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface QuestionRepository extends CrudRepository<Question, Integer> {

	@Query("from Question q where q.testID.id = :id")
	List<Question> findQuestionByTestID(@Param("id") int id);
}
