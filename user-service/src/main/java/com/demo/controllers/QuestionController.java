package com.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dtos.QuestionDTO;
import com.demo.dtos.TestDTO;
import com.demo.services.QuestionService;
import com.demo.services.TestService;

@Controller
@RequestMapping("api/question")
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	
	
	@GetMapping(value = "findByTestID/{testID}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<QuestionDTO>> findAll(@PathVariable("testID") int testID){
		try {
			return new ResponseEntity<List<QuestionDTO>>(questionService.findByTestID(testID), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<QuestionDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
//	@GetMapping(value = "findTestByCode/{code}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
//	public ResponseEntity<TestDTO> findTestByCode(@PathVariable("code") String code){
//		try {
//			return new ResponseEntity<TestDTO>(testService.findTestByCode(code), HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<TestDTO>(HttpStatus.BAD_REQUEST);
//		}
//	}
}
