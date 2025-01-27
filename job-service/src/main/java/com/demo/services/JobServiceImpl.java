package com.demo.services;

import com.demo.dtos.JobDTO;
import com.demo.entities.Job;
import com.demo.repositories.JobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;


@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private JobRepository jobRepository;
    @Override
    public List<JobDTO> findAll() {
        return mapper.map(jobRepository.findAll(), new TypeToken<List<JobDTO>>() {}.getType());
    }
}
