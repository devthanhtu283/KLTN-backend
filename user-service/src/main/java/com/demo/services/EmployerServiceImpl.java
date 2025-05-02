package com.demo.services;

import com.demo.dtos.SeekerDTO;
import com.demo.entities.Seeker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.demo.dtos.EmployerDTO;
import com.demo.entities.Employer;
import com.demo.repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean save(EmployerDTO employerDTO) {
        try {
            Employer employer = modelMapper.map(employerDTO, Employer.class);
            employeeRepository.save(employer);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public EmployerDTO findById(int id) {
        Optional<Employer> employerOptional = employeeRepository.findById(id);
        Employer employer = null;
        EmployerDTO employerDTO = null;
        if (employerOptional.isPresent()) {
            employer = employerOptional.get();

            employerDTO = modelMapper.map(employer, EmployerDTO.class);


        }

        return employerDTO;
    }

    @Override
    public Page<EmployerDTO> getLargeCompanies(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);

        return employeeRepository.findLargeCompanies(pageable).map(employer -> modelMapper.map(employer, EmployerDTO.class));
    }

    @Override
    public Page<EmployerDTO> getMediumAndSmallCompanies(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);

        return employeeRepository.findMediumAndSmallCompanies(pageable).map(employer -> modelMapper.map(employer, EmployerDTO.class));
    }

    @Override
    public List<EmployerDTO> searchByKeyword(String name) {
        return employeeRepository.findByCompanyNameContainingIgnoreCaseAndStatusTrue(name).stream()
                .map(employer -> modelMapper.map(employer, EmployerDTO.class))
                .toList();
    }

}
