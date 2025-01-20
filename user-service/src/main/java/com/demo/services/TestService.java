package com.demo.services;

import java.util.List;

import com.demo.dtos.TestDTO;

public interface TestService {
	public List<TestDTO> findAll();
	public TestDTO findTestByCode(String code);
}
