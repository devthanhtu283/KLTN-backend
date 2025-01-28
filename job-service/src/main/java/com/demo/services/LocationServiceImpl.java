package com.demo.services;

import com.demo.dtos.ExperienceDTO;
import com.demo.dtos.LocationDTO;
import com.demo.repositories.ExperienceRepository;
import com.demo.repositories.LocationRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ModelMapper mapper; // Sử dụng ModelMapper để chuyển đổi giữa các đối tượng


    @Override
    public List<LocationDTO> findAll() {
        return mapper.map(locationRepository.findAll(), new TypeToken<List<LocationDTO>>(){}.getType());
    }
}