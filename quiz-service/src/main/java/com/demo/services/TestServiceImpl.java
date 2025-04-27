package com.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import com.demo.dtos.AnswerDTO1;
import com.demo.dtos.QuestionDTO1;
import com.demo.dtos.TestDTO1;
import com.demo.entities.Test;
import com.demo.entities.User;
import com.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dtos.TestDTO;
import com.demo.repositories.TestRepository;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestRepository testRepository;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<TestDTO> findAll() {
		return mapper.map(testRepository.findAll(), new TypeToken<List<TestDTO>>() {}.getType());
	}

	@Override
	public TestDTO findTestByCode(String code) {
		return mapper.map(testRepository.findTestByCode(code), TestDTO.class);
	}

	@Override
	public List<TestDTO1> getTestsByUserId(Integer userId) {
		return testRepository.findByUserId(userId)
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public TestDTO1 createTest(TestDTO1 testDTO) {
		User user = userRepository.findById(testDTO.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));

		Test test = new Test();
		test.setUser(user);
		test.setTitle(testDTO.getTitle());
		test.setDescription(testDTO.getDescription());
		test.setCode(testDTO.getCode());
		test.setTime(testDTO.getTime());

		Test savedTest = testRepository.save(test);
		return convertToDTO(savedTest);
	}

	@Override
	public TestDTO1 getTestById(Integer id) {
		Test test = testRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Test not found"));
		return convertToDTO(test);
	}

	@Override
	public TestDTO1 updateTest(TestDTO1 testDTO) {
		Test test = testRepository.findById(testDTO.getId())
				.orElseThrow(() -> new RuntimeException("Test not found"));

		User user = userRepository.findById(testDTO.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));

		test.setUser(user);
		test.setTitle(testDTO.getTitle());
		test.setDescription(testDTO.getDescription());
		test.setCode(testDTO.getCode());
		test.setTime(testDTO.getTime());

		Test updatedTest = testRepository.save(test);
		return convertToDTO(updatedTest);
	}

	@Override
	public void deleteTest(Integer id) {
		Test test = testRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Test not found"));
		testRepository.delete(test);
	}

	private TestDTO1 convertToDTO(Test test) {
		TestDTO1 dto = new TestDTO1();
		dto.setId(test.getId());
		dto.setUserId(test.getUser().getId());
		dto.setTitle(test.getTitle());
		dto.setDescription(test.getDescription());
		dto.setCode(test.getCode());
		dto.setTime(test.getTime());
		dto.setQuestions(test.getQuestions().stream().map(question -> {
			QuestionDTO1 questionDTO = new QuestionDTO1();
			questionDTO.setId(question.getId());
			questionDTO.setTestId(question.getTest().getId());
			questionDTO.setQuestionType(question.getQuestionType());
			questionDTO.setContent(question.getContent());
			questionDTO.setAnswers(question.getAnswers().stream().map(answer -> {
				AnswerDTO1 answerDTO = new AnswerDTO1();
				answerDTO.setId(answer.getId());
				answerDTO.setQuestionId(answer.getQuestion().getId());
				answerDTO.setContent(answer.getContent());
				answerDTO.setIsCorrect(answer.getIsCorrect());
				return answerDTO;
			}).collect(Collectors.toSet()));
			return questionDTO;
		}).collect(Collectors.toSet()));
		return dto;
	}
}