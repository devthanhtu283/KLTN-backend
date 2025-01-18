package com.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.controllers.advice.ResourceNotFoundException;
import com.demo.entities.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.demo.dtos.TestDTO;
import com.demo.services.TestService;

@Controller
@RequestMapping("quiz")
public class TestController {
	@Autowired
	private TestService testService;
	
	
	@GetMapping(value = "findAll", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TestDTO>> findAll(){
		try {
			return new ResponseEntity<List<TestDTO>>(testService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Không tìm thấy dữ liệu");
		}
	}
	
	@GetMapping(value = "findTestByCode/{code}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findTestByCode(@PathVariable("code") String code){
		try {
			TestDTO testDTO = testService.findTestByCode(code);
			Map<String, Object> response = new HashMap<>();
			response.put("data", testDTO);
			response.put("msg", "success");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResourceNotFoundException("Không tìm thấy dữ liệu với mã: " + code);
		}
	}
}
