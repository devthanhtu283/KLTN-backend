package com.demo.services;

import com.demo.dtos.JobDTO;
import com.demo.entities.Job;
import com.demo.repositories.JobPaginationRepository;
import com.demo.repositories.JobRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import org.modelmapper.TypeToken;

@Service
public class JobServiceImpl implements JobService {

    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobPaginationRepository jobPaginationRepository;
    @Autowired
    private ModelMapper mapper; // Sử dụng ModelMapper để chuyển đổi giữa các đối tượng


    @Override
    public List<JobDTO> findAll() {
        logger.info("⚡ Fetching jobs from database...");
        return mapper.map(jobRepository.findAll(), new TypeToken<List<JobDTO>>() {
        }.getType());
    }

    @Override
    public JobDTO findById(int id) {
        return mapper.map(jobRepository.findById(id).get(), JobDTO.class);
    }

    @Override
    public Page<JobDTO> findAllPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Direction.DESC, "id")); // 🔥 Sắp xếp theo ID giảm dần
        return jobPaginationRepository.findAll(pageable).map(job -> mapper.map(job, JobDTO.class));
    }

    @Override
    public Page<JobDTO> searchJobs(String title, Integer locationId, Integer worktypeId, Integer experienceId, Integer categoryId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Direction.DESC, "id")); // 🔥 Sắp xếp theo ID giảm dần
        return jobPaginationRepository.searchJobs(title, locationId, worktypeId, experienceId, categoryId, pageable).map(job -> mapper.map(job, JobDTO.class));
    }

    @Override
    public Page<JobDTO> findByEmployeeIdPagination(int employeeId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Direction.DESC, "id")); // 🔥 Sắp xếp theo ID giảm dần
        return jobPaginationRepository.findByEmployerId(employeeId, pageable).map(job -> mapper.map(job, JobDTO.class));
    }

    @Override
    public Page<JobDTO> searchByTitle(String title, int employerId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        return jobPaginationRepository.findByEmployerIdAndTitleContainingIgnoreCase(employerId, title, pageable)
                .map(job -> mapper.map(job, JobDTO.class));
    }


    @Override
    public List<JobDTO> findByEmployeeId(int employeeId) {
        return jobRepository.findByEmployerId(employeeId, true)
                .stream()
                .map(job -> mapper.map(job, JobDTO.class))
                .toList();
    }

    @Override
    public boolean save(JobDTO jobDTO) {
        try {
            Job job = mapper.map(jobDTO, Job.class);
            job.setStatus(true);
            job.setPostedAt(new Date());
//            ObjectMapper objectMapper = new ObjectMapper();
//            job.setDescriptionJson(objectMapper.writeValueAsString(jobDTO.getDescriptionJson()));

            jobRepository.save(job);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int jobId) {
        try {
            Job job = jobRepository.findById(jobId).get();
            job.setStatus(false);
            jobRepository.save(job);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




}