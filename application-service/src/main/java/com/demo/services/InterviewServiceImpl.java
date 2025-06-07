package com.demo.services;

import com.demo.dtos.InterviewDTO;
import com.demo.entities.Interview;
import com.demo.helpers.NullProperty;
import com.demo.repository.InterviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<InterviewDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return interviewRepository.findAll(pageable).map(x -> modelMapper.map(x, InterviewDTO.class));
    }

    @Override
    public boolean save(InterviewDTO interviewDTO) {
        try {
            Interview interview = modelMapper.map(interviewDTO, Interview.class);
            interviewRepository.save(interview);
            return true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InterviewDTO update(InterviewDTO interviewDTO) {
        Optional<Interview> interview = interviewRepository.findById(interviewDTO.getId());
        if (interview.isPresent()) {
            Interview existingInterview = interview.get();

            BeanUtils.copyProperties(interviewDTO, existingInterview, NullProperty.getNullPropertyNames(interviewDTO));

            Interview res = interviewRepository.save(existingInterview);

            return modelMapper.map(res, InterviewDTO.class);
        }
        return null;
    }

    @Override
    public Page<InterviewDTO> listInterviewsOfEmployer(int employeeId, Pageable pageable) {
        return interviewRepository.listInterviewsOfEmployer(employeeId, pageable)
                .map(interviewDTO -> modelMapper.map(interviewDTO, InterviewDTO.class));
    }

    @Override
    public Page<InterviewDTO> listInterviewsOfSeeker(int seekerId, Pageable pageable) {
        return interviewRepository.listInterviewsOfSeeker(seekerId, pageable)
                .map(interviewDTO -> modelMapper.map(interviewDTO, InterviewDTO.class));
    }
}
