package com.demo.services;

import com.demo.dtos.ApplicationDTO;
import com.demo.entities.Application;
import com.demo.entities.Job;
import com.demo.entities.User;
import com.demo.events.JobEvent;
import com.demo.helpers.PageResult;
import com.demo.repository.ApplicationRepository;
import com.demo.repository.JobRepository;
import com.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "applications")
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    @Cacheable(key = "'all'")
    public List<ApplicationDTO> listApplications() {
        return modelMapper.map(applicationRepository.findAll(), new TypeToken<List<ApplicationDTO>>() {
        }.getType());
    }

    @Override
    @CacheEvict(allEntries = true)
    public boolean save(ApplicationDTO applicationDTO) {
        try {
            Application application = modelMapper.map(applicationDTO, Application.class);
            applicationRepository.save(application);
            return true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Cacheable(key = "#id")
    public ApplicationDTO findById(int id) {
        try {
            return applicationRepository.findById(id).map(application -> modelMapper.map(application, ApplicationDTO.class)).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Cacheable(key = "'listByJobId_' + #jobId + '_' + #page + '_' + #size + '_' + #status")
    public PageResult<ApplicationDTO> listApplicationByJobId(int jobId, int page, int size, int status) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Application> applicationPage = applicationRepository.listApplicationByJobId(jobId, pageable, status);

            List<ApplicationDTO> applicationDTOs = applicationPage.getContent()
                    .stream()
                    .map(application -> modelMapper.map(application, ApplicationDTO.class))
                    .collect(Collectors.toList());
            return new PageResult<>(
                    applicationDTOs,
                    applicationPage.getNumber(),
                    applicationPage.getSize(),
                    applicationPage.getTotalElements(),
                    applicationPage.getTotalPages()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Cacheable(key = "'listByEmployerId_' + #employerId + '_' + #page + '_' + #size + '_' + #status")
    public PageResult<ApplicationDTO> listDistinctApplicationByEmployerId(int employerId, int page, int size, int status) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Application> applicationPage = applicationRepository.listDistinctApplicationByEmployerId(employerId, pageable, status);

            // Ánh xạ từng phần tử trong Page<Application> sang ApplicationDTO
            List<ApplicationDTO> applicationDTOs = applicationPage.getContent()
                    .stream()
                    .map(application -> modelMapper.map(application, ApplicationDTO.class))
                    .collect(Collectors.toList());

            // Tạo đối tượng Page<ApplicationDTO> mới
            return new PageResult<>(
                    applicationDTOs,
                    applicationPage.getNumber(),
                    applicationPage.getSize(),
                    applicationPage.getTotalElements(),
                    applicationPage.getTotalPages()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Page<ApplicationDTO> searchApplication(String jobTitle, String seekerName, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);


            // Kiểm tra null
            jobTitle = (jobTitle == null || jobTitle.isBlank()) ? "" : jobTitle.toLowerCase();
            seekerName = (seekerName == null || seekerName.isBlank()) ? "" : seekerName.toLowerCase();

            System.out.println("🔍 Tìm kiếm với jobTitle: " + jobTitle + ", seekerName: " + seekerName);


            return applicationRepository.searchApplication(jobTitle, seekerName, pageable)
                    .map(application -> modelMapper.map(application, ApplicationDTO.class));
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm kiếm: " + e.getMessage(), e);
        }
    }

    @Override
    @Cacheable(key = "'listSeekerApplied_' + #seekerId + '_' + #page + '_' + #size + '_' + #status")
    public PageResult<ApplicationDTO> listSeekerApplied(int seekerId, int page, int size, int status) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Application> applicationPage = applicationRepository.listSeekerApplied(seekerId, pageable, status);
        // Ánh xạ từng phần tử trong Page<Application> sang ApplicationDTO
        List<ApplicationDTO> applicationDTOs = applicationPage.getContent()
                .stream()
                .map(application -> modelMapper.map(application, ApplicationDTO.class))
                .collect(Collectors.toList());

        // Tạo đối tượng Page<ApplicationDTO> mới
        return new PageResult<>(
                applicationDTOs,
                applicationPage.getNumber(),
                applicationPage.getSize(),
                applicationPage.getTotalElements(),
                applicationPage.getTotalPages()
        );
    }

    @Override
    @Cacheable(key = "'history_' + #seekerId + '_' + #page + '_' + #size")
    public PageResult<ApplicationDTO> historyApplication(int seekerId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Application> applicationPage = applicationRepository.historyApplication(seekerId, pageable);
        // Ánh xạ từng phần tử trong Page<Application> sang ApplicationDTO
        List<ApplicationDTO> applicationDTOs = applicationPage.getContent()
                .stream()
                .map(application -> modelMapper.map(application, ApplicationDTO.class))
                .collect(Collectors.toList());

        // Tạo đối tượng Page<ApplicationDTO> mới
        return new PageResult<>(
                applicationDTOs,
                applicationPage.getNumber(),
                applicationPage.getSize(),
                applicationPage.getTotalElements(),
                applicationPage.getTotalPages()
        );
    }


    @Override
    @CacheEvict(allEntries = true)
    public ApplicationDTO updateStatus(int id, int status) {
        try {
            // Tìm đơn ứng tuyển hiện tại
            ApplicationDTO applicationDTO = findById(id);
            Job job = jobRepository.findById(applicationDTO.getJobId()).get();
            User seeker = userRepository.findById(applicationDTO.getSeekerId()).get();
            List<User> followers = new ArrayList<>();
            followers.add(seeker);
            if (applicationDTO != null) {
                // Cập nhật trạng thái cho đơn ứng tuyển hiện tại
                applicationDTO.setStatus(status);
                Application application = modelMapper.map(applicationDTO, Application.class);
                applicationRepository.save(application);
                switch (status) {
                    case 1:
                        eventPublisher.publishEvent(new JobEvent(this, followers, job, seeker, job.getTitle(), "APPLICATION_SEEN"));
                        break;
                    case 2:
                        eventPublisher.publishEvent(new JobEvent(this, followers, job, seeker, job.getTitle(), "APPLICATION_ACCEPTED"));
                        break;
                    case 3:
                        eventPublisher.publishEvent(new JobEvent(this, followers, job, seeker, job.getTitle(), "APPLICATION_REJECTED"));
                        break;
                    default:
                        break;
                }

                List<Application> allApplicationsForJobs = applicationRepository.listSeekerAppliedForJob(applicationDTO.getSeekerId(), applicationDTO.getJobId());

                for (Application app : allApplicationsForJobs) {
                    if (app.getStatus() != status) {
                        app.setStatus(status);
                        applicationRepository.save(app);
                    }
                }
            }
            return applicationDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


//    @Override
//    public void saveDBIntoElasticsearch() {
//        List<Application> applications = applicationRepository.findAll();
//
//        // Chuyển đổi sang DTO trước khi lưu vào Elasticsearch
//        List<ApplicationIndex> applicationsForES = applications.stream()
//                .map(ApplicationIndex::new) // 🔥 Chuyển đổi sang ApplicationIndex trước khi lưu
//                .collect(Collectors.toList());
//
//        applicationElasticsearchRepository.saveAll(applicationsForES);
//    }

    @Override
    @Cacheable(key = "'count_apply_' + #seekerId + '_' + #jobId")
    public int countApply(int seekerId, int jobId) {
        return applicationRepository.countApply(seekerId, jobId);
    }

    @Override
    @Cacheable(key = "'count_applicants_' + #jobId")
    public int countApplicantsByJobId(int jobId) {
        return applicationRepository.countApplicantsByJobId(jobId);
    }


}
