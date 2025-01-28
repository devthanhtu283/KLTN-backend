package com.demo.services;

import com.demo.dtos.ExperienceDTO;
import com.demo.dtos.JobDTO;
import com.demo.repositories.ExperienceRepository;
import com.demo.repositories.JobPaginationRepository;
import com.demo.repositories.JobRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceServiceImpl implements ExperienceService{

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private ModelMapper mapper; // Sử dụng ModelMapper để chuyển đổi giữa các đối tượng


    @Override
    public List<ExperienceDTO> findAll() {
        return mapper.map(experienceRepository.findAll(), new TypeToken<List<ExperienceDTO>>(){}.getType());
    }
}