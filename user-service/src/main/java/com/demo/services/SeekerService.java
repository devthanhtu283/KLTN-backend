package com.demo.services;

import com.demo.dtos.SeekerDTO;


public interface SeekerService {
	
	public boolean save(SeekerDTO seekerDTO);

	public boolean update(SeekerDTO seekerDTO);
	
	public SeekerDTO findById(int id);


}
