package com.demo.services;

import com.demo.dto.ApplicationDTO;
import com.demo.entities.Application;
import com.demo.repository.ApplicationRepository;
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


}
