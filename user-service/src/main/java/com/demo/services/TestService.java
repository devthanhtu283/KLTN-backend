package com.demo.services;

import java.util.List;

import com.demo.dtos.TestDTO;
import com.demo.entities.Test;

public interface TestService {
	public List<TestDTO> findAll();
	public TestDTO findTestByCode(String code);
}
