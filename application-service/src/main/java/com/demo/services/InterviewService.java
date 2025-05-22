package com.demo.services;

import com.demo.dto.InterviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InterviewService {

    public Page<InterviewDTO> findAll(int page, int size);

    public boolean save(InterviewDTO interviewDTO);

    public InterviewDTO update(InterviewDTO interviewDTO);

    public Page<InterviewDTO> listInterviewsOfEmployer(int employeeId, Pageable pageable);

    public Page<InterviewDTO> listInterviewsOfSeeker(int seekerId, Pageable pageable);
}
