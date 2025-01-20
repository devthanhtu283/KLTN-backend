package com.demo.services;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dtos.SeekerDTO;
import com.demo.entities.Seeker;
import com.demo.repositories.SeekerRepository;

@Service
public class SeekerServiceImpl implements SeekerService {
	
	@Autowired
	private SeekerRepository seekerRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public boolean save(SeekerDTO seekerDTO) {
		try {
			Seeker seeker = seekerRepository.findById(seekerDTO.getId())
		                .orElseThrow(() -> new RuntimeException("Seeker not found"));
			seeker = modelMapper.map(seekerDTO, Seeker.class);
			seekerRepository.save(seeker);
			return true;			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public SeekerDTO findById(int id) {
		// TODO Auto-generated method stub
		return modelMapper.map(seekerRepository.findById(id).get(), SeekerDTO.class);
	}

}
