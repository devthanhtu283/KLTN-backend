package com.demo.services;

import java.util.List;

import com.demo.dtos.TestDTO;
import com.demo.dtos.TestDTO1;

public interface TestService {
	List<TestDTO> findAll();
	TestDTO findTestByCode(String code);
	List<TestDTO1> getTestsByUserId(Integer userId);
	TestDTO1 createTest(TestDTO1 testDTO);
	TestDTO1 getTestById(Integer id);
	TestDTO1 updateTest(TestDTO1 testDTO);
	void deleteTest(Integer id);
}
