package com.demo.services;

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
            user.setId(testHistoryDTO.getUserID());
            Test test = new Test();
            test.setId(testHistoryDTO.getTestID());
            Testhistory testhistory = modelMapper.map(testHistoryDTO, Testhistory.class);
            testhistory.setTimeSubmit(LocalDateTime.now());
            testHistoryRepository.save(testhistory);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }

    }
}
