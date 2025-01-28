package com.demo.services;

import com.demo.dtos.JobDTO;
import com.demo.entities.Job;
import com.demo.repositories.JobPaginationRepository;
import com.demo.repositories.JobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import org.modelmapper.TypeToken;

@Service
public class JobServiceImpl implements JobService{

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobPaginationRepository jobPaginationRepository;
    @Autowired
    private ModelMapper mapper; // Sử dụng ModelMapper để chuyển đổi giữa các đối tượng


    @Override
    public List<JobDTO> findAll() {
        return mapper.map(jobRepository.findAll(), new TypeToken<List<JobDTO>>() {}.getType());
    }

    @Override
    public JobDTO findById(int id) {
        return mapper.map(jobRepository.findById(id).get(), JobDTO.class);
    }

    @Override
    public Page<JobDTO> findAllPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return jobPaginationRepository.findAll(pageable).map(job -> mapper.map(job, JobDTO.class));
    }

    @Override
    public Page<JobDTO> searchJobs(String title, Integer locationId, Integer worktypeId, Integer experienceId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return jobPaginationRepository.searchJobs(title, locationId, worktypeId, experienceId, pageable).map(job -> mapper.map(job, JobDTO.class));
    }


}