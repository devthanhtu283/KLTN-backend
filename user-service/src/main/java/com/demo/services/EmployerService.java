package com.demo.services;

import com.demo.dtos.EmployerDTO;
import com.demo.dtos.SeekerDTO;

public interface EmployerService {
	
	public boolean save(EmployerDTO employerDTO);
	
	public EmployerDTO findById(int id);
}
