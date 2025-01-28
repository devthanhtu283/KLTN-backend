package com.demo.services;

import com.demo.dtos.JobDTO;
import com.demo.dtos.LocationDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LocationService
{
    public List<LocationDTO> findAll();

}
