package com.demo.services;

import com.demo.dtos.JobDTO;
import com.demo.dtos.WorktypeDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WorktypeService
{
    public List<WorktypeDTO> findAll();

}
