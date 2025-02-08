package com.demo.services;

import com.demo.dtos.FeedbackDTO;
import com.demo.entities.Feedback;

public interface FeedbackService {
    public boolean saveFeedback(FeedbackDTO feedback);
}
