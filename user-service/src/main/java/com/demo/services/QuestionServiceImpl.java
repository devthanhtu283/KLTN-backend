package com.demo.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dtos.QuestionDTO;
import com.demo.dtos.TestDTO;
import com.demo.repositories.QuestionRepository;
import com.demo.repositories.TestRepository;

@Service
public class QuestionServiceImpl implements QuestionService{
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private ModelMapper mapper;
	@Override
	public List<QuestionDTO> findByTestID(int testID) {
		return mapper.map(questionRepository.findQuestionByTestID(testID), new TypeToken<List<QuestionDTO>>() {}.getType());
	}

}
