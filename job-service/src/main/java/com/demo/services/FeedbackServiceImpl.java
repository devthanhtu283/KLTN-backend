package com.demo.services;

import com.demo.dtos.FeedbackDTO;
import com.demo.entities.Feedback;
import com.demo.entities.User;
import com.demo.repositories.FeedbackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public boolean saveFeedback(FeedbackDTO feedbackDTO) {
        try {
            Feedback feedback = modelMapper.map(feedbackDTO, Feedback.class);
            feedback.setCreatedAt(new Date());
            feedback.setStatus(true);
            feedbackRepository.save(feedback);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
