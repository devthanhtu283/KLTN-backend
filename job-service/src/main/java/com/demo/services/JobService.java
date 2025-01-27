package com.demo.services;

import com.demo.dtos.JobDTO;
import com.demo.entities.Job;

import java.util.List;

public interface JobService
{
    public List<JobDTO> findAll();

    public JobDTO findById(int id);
}
