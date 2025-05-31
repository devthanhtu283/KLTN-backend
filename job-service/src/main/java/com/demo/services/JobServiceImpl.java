package com.demo.services;

import com.demo.dtos.JobDTO;
import com.demo.entities.Employermembership;
import com.demo.entities.Job;
import com.demo.entities.Membership;
import com.demo.repositories.EmployerMembershipRepository;
import com.demo.repositories.JobPaginationRepository;
import com.demo.repositories.JobRepository;
import com.demo.repositories.MembershipRepository;
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

import java.time.LocalDate;
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
    private EmployerMembershipRepository employerMembershipRepository;
    @Autowired
    private MembershipRepository membershipRepository;
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
    public Page<JobDTO> getAllJobAdmin(String search, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return jobRepository.getAllJobdAdmin(search, pageable).map(job -> mapper.map(job, JobDTO.class));
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
            if (checkValidCreateJob(jobDTO.getEmployerId())) {
                jobRepository.save(job); // Lưu bài đăng
                System.out.println("Đăng bài thành công");
                return true;
            } else {
                System.out.println("Không thể đăng bài: Gói không hợp lệ hoặc vượt giới hạn");
                return false;
            }
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


    private boolean checkValidCreateJob(Integer employeeID) {
        Employermembership employermembership = employerMembershipRepository.findByUserId(employeeID);
        if (employermembership == null || !employermembership.isStatus() || convertToLocalDate(employermembership.getEndDate()).isBefore(LocalDate.now())) {
            return false;
        }

        Membership membership = membershipRepository.findById(employermembership.getMembership().getId()).get();
        if (membership.getDuration().equalsIgnoreCase("YEARLY")) {
            return true;
        } else if (membership.getDuration().equalsIgnoreCase("MONTHLY")) {
            LocalDate startDate = convertToLocalDate(employermembership.getStartDate());
            LocalDate endDate = convertToLocalDate(employermembership.getEndDate());
            long jobCount = jobRepository.countByEmployerIdAndPostedAtBetween(
                    employeeID,
                    Date.from(startDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()),
                    Date.from(endDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant())
            );
            return jobCount < 5;
        } else if (membership.getDuration().equalsIgnoreCase("MONTHLY") && membership.getPrice() == 0) {
            LocalDate startDate = convertToLocalDate(employermembership.getStartDate());
            LocalDate endDate = convertToLocalDate(employermembership.getEndDate());
            long jobCount = jobRepository.countByEmployerIdAndPostedAtBetween(
                    employeeID,
                    Date.from(startDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()),
                    Date.from(endDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant())
            );
            return jobCount < 1;
        }
        return false;
    }

    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
    }


}