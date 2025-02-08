package com.demo.services;

import com.demo.dtos.JobDTO;
import com.demo.entities.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobService
{
    public List<JobDTO> findAll();

    public JobDTO findById(int id);

    public Page<JobDTO> findAllPagination(int pageNo, int pageSize);

    public Page<JobDTO> searchJobs(String title, Integer locationId, Integer worktypeId, Integer experienceId, int pageNo, int pageSize);

    public Page<JobDTO> findByEmployeeIdPagination(int employeeId, int pageNo, int pageSize);

    public boolean save(JobDTO jobDTO);

    public boolean delete(int jobId);

}
