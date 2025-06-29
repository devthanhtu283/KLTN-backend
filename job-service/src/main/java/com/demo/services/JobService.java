package com.demo.services;

import com.demo.dtos.JobDTO;
import com.demo.entities.Job;
import com.demo.helpers.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface JobService {
    public List<JobDTO> findAll();

    public JobDTO findById(int id);

    public PageResult<JobDTO> findAllPagination(int pageNo, int pageSize);

    public PageResult<JobDTO> searchJobs(String title, Integer locationId, Integer worktypeId, Integer experienceId, Integer categoryId, int pageNo, int pageSize);

    public PageResult<JobDTO> findByEmployeeIdPagination(int employeeId, int pageNo, int pageSize);

    public List<JobDTO> findByEmployeeId(int employeeId);

    public JobDTO save(JobDTO jobDTO);

    public boolean delete(int jobId);

    public PageResult<JobDTO> searchByTitle(String title, int employerId, int pageNo, int pageSize);

    public PageResult<JobDTO> getAllJobAdmin(String search, int pageNo, int pageSize);


}
