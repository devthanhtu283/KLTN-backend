package com.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.demo.dtos.EmployerDTO;
import com.demo.entities.Employer;
import com.demo.repositories.EmployeeRepository;

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
		// TODO Auto-generated method stub
		return modelMapper.map(employeeRepository.findById(id).get(), EmployerDTO.class);
	}

}
