package com.demo.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.demo.dtos.TestDTO;

import com.demo.repositories.TestRepository;
@Service
public class TestServiceImpl implements TestService{
	@Autowired
	private TestRepository testRepository;
	@Autowired
	private ModelMapper mapper;
	@Override
	public List<TestDTO> findAll() {
		return mapper.map(testRepository.findAll(), new TypeToken<List<TestDTO>>() {}.getType());
	}
	@Override
	public TestDTO findTestByCode(String code) {
		return mapper.map(testRepository.findTestByCode(code), TestDTO.class);
	}

}
