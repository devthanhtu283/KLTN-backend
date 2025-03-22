package com.demo.services;

import com.demo.dto.InterviewDTO;
import com.demo.entities.Interview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InterviewService {

    public boolean save(InterviewDTO interviewDTO);

    public InterviewDTO update(InterviewDTO interviewDTO);

    public Page<InterviewDTO> listInterviewsOfEmployer(int employeeId, Pageable pageable);

    public Page<InterviewDTO> listInterviewsOfSeeker(int seekerId, Pageable pageable);
}
