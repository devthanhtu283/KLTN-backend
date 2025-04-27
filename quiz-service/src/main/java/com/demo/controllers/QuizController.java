package com.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.controllers.advice.ResourceNotFoundException;
import com.demo.dtos.*;
import com.demo.services.AnswerService;
import com.demo.services.QuestionService;
import com.demo.services.TestHistoryService;
import com.demo.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private TestService testService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private TestHistoryService testHistoryService;

    @Autowired
    private AnswerService answerService;
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
    @PostMapping(value = "testHistory/save", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody TestHistoryDTO testHistoryDTO){
        try {
            return new ResponseEntity<Object>(new Object() {
                public boolean status = testHistoryService.save(testHistoryDTO);
            }, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "testHistory/checkDone/{userId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> checkDoneTestHistory(@PathVariable("userId") int userId) {
        try {
            return new ResponseEntity<>(testHistoryService.checkDone(userId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "testHistory/findByUserIdAndTestId/{userId}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByUserIdAndTestId(@PathVariable("userId") int userId) {
        try {
            TestHistoryDTO testHistoryDTO = testHistoryService.findByUserIdAndTestId(userId);
            return new ResponseEntity<>(testHistoryDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "testHistory/update", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@RequestBody TestHistoryDTO testHistoryDTO) {
        try {
            return new ResponseEntity<>(new Object() {
                public boolean status = testHistoryService.update(testHistoryDTO);
            }, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("test/findTestByUserId/{userId}")
    public ResponseEntity<List<TestDTO1>> getTestsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(testService.getTestsByUserId(userId));
    }

    @PostMapping(value = "test/save", produces = "application/json", consumes = "application/json")
    public ResponseEntity<TestDTO1> createTest(@RequestBody TestDTO1 testDTO) {
        return ResponseEntity.ok(testService.createTest(testDTO));
    }

    @PutMapping(value = "test/update/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<TestDTO1> updateTest(@PathVariable Integer id, @RequestBody TestDTO1 testDTO) {
        testDTO.setId(id);
        return ResponseEntity.ok(testService.updateTest(testDTO));
    }

    @DeleteMapping("test/delete/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Integer id) {
        testService.deleteTest(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("test/getTestById/{id}")
    public ResponseEntity<TestDTO1> getTestById(@PathVariable Integer id) {
        return ResponseEntity.ok(testService.getTestById(id));
    }

    @PostMapping(value = "question/save", produces = "application/json", consumes = "application/json")
    public ResponseEntity<QuestionDTO1> createQuestion(@RequestBody QuestionDTO1 questionDTO) {
        return ResponseEntity.ok(questionService.createQuestion(questionDTO));
    }

    @PutMapping(value = "question/update/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<QuestionDTO1> updateQuestion(@PathVariable Integer id, @RequestBody QuestionDTO1 questionDTO) {
        questionDTO.setId(id);
        return ResponseEntity.ok(questionService.updateQuestion(questionDTO));
    }

    @DeleteMapping("question/delete/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "answer/save", produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<AnswerDTO1>> createAnswers(@RequestBody List<AnswerDTO1> answerDTOs) {
        return ResponseEntity.ok(answerService.createAnswers(answerDTOs));
    }
}