package com.demo.services;

import com.demo.dtos.ExperienceDTO;
import com.demo.dtos.JobDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExperienceService
{
    public List<ExperienceDTO> findAll();

}
