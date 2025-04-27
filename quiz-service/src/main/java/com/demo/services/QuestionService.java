package com.demo.services;

import java.util.List;

import com.demo.dtos.QuestionDTO;
import com.demo.dtos.QuestionDTO1;

public interface QuestionService {
	List<QuestionDTO> findByTestID(int testID);
	QuestionDTO1 createQuestion(QuestionDTO1 questionDTO);
	QuestionDTO1 updateQuestion(QuestionDTO1 questionDTO);
	void deleteQuestion(Integer id);
}
