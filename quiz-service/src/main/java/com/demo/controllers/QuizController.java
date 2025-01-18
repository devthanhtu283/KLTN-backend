package com.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.controllers.advice.ResourceNotFoundException;
import com.demo.dtos.QuestionDTO;
import com.demo.dtos.TestDTO;
import com.demo.services.QuestionService;
import com.demo.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private TestService testService;

    @Autowired
    private QuestionService questionService;

    // Các endpoint liên quan đến Test
    @GetMapping(value = "test/findAll", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TestDTO>> findAllTests() {
        try {
            return new ResponseEntity<>(testService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Không tìm thấy dữ liệu");
        }
    }

    @GetMapping(value = "test/findTestByCode/{code}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findTestByCode(@PathVariable("code") String code) {
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

    // Các endpoint liên quan đến Question
    @GetMapping(value = "question/findByTestID/{testID}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<QuestionDTO>> findQuestionsByTestID(@PathVariable("testID") int testID) {
        try {
            return new ResponseEntity<>(questionService.findByTestID(testID), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}