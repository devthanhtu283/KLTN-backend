package com.demo.services;

import com.demo.dto.ApplicationDTO;
import com.demo.dto.ApplicationIndex;
import com.demo.entities.Application;
import com.demo.repository.elasticsearch.ApplicationElasticsearchRepository;
import com.demo.repository.jpa.ApplicationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationElasticsearchRepository applicationElasticsearchRepository;

    @Override
    public List<ApplicationDTO> listApplications() {
        return modelMapper.map(applicationRepository.findAll(), new TypeToken<List<ApplicationDTO>>() {}.getType());
    }

    @Override
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
    public ApplicationDTO findById(int id) {
        try {
            return applicationRepository.findById(id).map(application -> modelMapper.map(application, ApplicationDTO.class)).orElse(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Page<ApplicationDTO> listApplicationByEmployerId(int employerId, int page, int size, int status) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Application> applicationPage = applicationRepository.listApplicationByEmployerId(employerId, pageable, status);

            // Ánh xạ từng phần tử trong Page<Application> sang ApplicationDTO
            List<ApplicationDTO> applicationDTOs = applicationPage.getContent()
                    .stream()
                    .map(application -> modelMapper.map(application, ApplicationDTO.class))
                    .collect(Collectors.toList());

            // Tạo đối tượng Page<ApplicationDTO> mới
            return new PageImpl<>(applicationDTOs, pageable, applicationPage.getTotalElements());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Page<ApplicationIndex> searchApplication(String jobTitle, String seekerName, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);


            // Kiểm tra null
            jobTitle = (jobTitle == null || jobTitle.isBlank()) ? "" : jobTitle.toLowerCase();
            seekerName = (seekerName == null || seekerName.isBlank()) ? "" : seekerName.toLowerCase();

            System.out.println("🔍 Tìm kiếm với jobTitle: " + jobTitle + ", seekerName: " + seekerName);


            Page<ApplicationIndex> results = applicationElasticsearchRepository.searchApplication(jobTitle, seekerName, pageable);

            System.out.println("🔍 Kết quả tìm kiếm: " + results.getTotalElements() + " records");

            return results;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm kiếm: " + e.getMessage(), e);
        }
    }




    @Override
    public ApplicationDTO updateStatus(int id, int status) {
        try {
            ApplicationDTO applicationDTO = findById(id);
            if(applicationDTO != null) {
                applicationDTO.setStatus(status);
                Application application = modelMapper.map(applicationDTO, Application.class);
                applicationRepository.save(application);
            }
            return applicationDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveDBIntoElasticsearch() {
        List<Application> applications = applicationRepository.findAll();

        // Chuyển đổi sang DTO trước khi lưu vào Elasticsearch
        List<ApplicationIndex> applicationsForES = applications.stream()
                .map(ApplicationIndex::new) // 🔥 Chuyển đổi sang ApplicationIndex trước khi lưu
                .collect(Collectors.toList());

        applicationElasticsearchRepository.saveAll(applicationsForES);
    }

    @Override
    public int countApply(int seekerId, int jobId) {
        return applicationRepository.countApply(seekerId, jobId);
    }


}
