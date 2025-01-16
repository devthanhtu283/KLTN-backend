package com.demo.services;

import java.util.List;

import com.demo.dtos.QuestionDTO;

public interface QuestionService {
	public List<QuestionDTO> findByTestID(int testID);
}
