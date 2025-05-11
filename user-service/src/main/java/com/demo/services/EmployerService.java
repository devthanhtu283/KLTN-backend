package com.demo.services;

import com.demo.dtos.EmployerDTO;
import com.demo.dtos.SeekerDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployerService {

    public boolean save(EmployerDTO employerDTO);

    public EmployerDTO findById(int id);

    public Page<EmployerDTO> getLargeCompanies(int page, int size);

    public Page<EmployerDTO> getMediumAndSmallCompanies(int page, int size);

    List<EmployerDTO> searchByKeyword(String name);
}
