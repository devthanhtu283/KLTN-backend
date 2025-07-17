package com.demo.services;

import com.demo.dtos.TestDTO1;
import com.demo.dtos.TestHistoryDTO;
import com.demo.entities.Employer;
import com.demo.entities.Test;
import com.demo.entities.Testhistory;
import com.demo.entities.User;
import com.demo.repositories.TestHistoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestHistoryServiceImpl implements TestHistoryService {
    @Autowired
    private TestHistoryRepository testHistoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public boolean save(TestHistoryDTO testHistoryDTO) {
        try {
            User user = new User();
            System.out.println(testHistoryDTO);
            user.setId(testHistoryDTO.getUserID());
            Test test = new Test();
            test.setId(testHistoryDTO.getTestID());
            Testhistory testhistory = modelMapper.map(testHistoryDTO, Testhistory.class);

            testHistoryRepository.save(testhistory);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean checkDone(int userId) {
        try {

            return testHistoryRepository.findByUserId(userId) != null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public TestHistoryDTO findByUserIdAndTestId(int userId) {
        try {
            Testhistory testhistory = testHistoryRepository.findByUserIdAndTestId(userId);
            if (testhistory == null) {
                return null;
            }
            return modelMapper.map(testhistory, TestHistoryDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(TestHistoryDTO testHistoryDTO) {
        try {
            Testhistory testhistory = testHistoryRepository.findById(testHistoryDTO.getId())
                    .orElseThrow(() -> new IllegalArgumentException("TestHistory not found with ID: " + testHistoryDTO.getId()));

            // Cập nhật các trường
            testhistory.setScore(testHistoryDTO.getScore());

            testhistory.setTimeSubmit(LocalDateTime.now());

            testHistoryRepository.save(testhistory);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
