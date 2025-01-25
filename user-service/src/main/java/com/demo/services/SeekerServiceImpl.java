package com.demo.services;

import java.util.Optional;

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
	public boolean update(SeekerDTO seekerDTO) {
		try {
			// Tìm Seeker đã tồn tại
			Seeker seeker = seekerRepository.findById(seekerDTO.getId())
					.orElseThrow(() -> new RuntimeException("Seeker not found"));

			// Cập nhật thông tin từ SeekerDTO
			modelMapper.map(seekerDTO, seeker);
			seekerRepository.save(seeker);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public SeekerDTO findById(int id) {
		Optional<Seeker> seekerOptional  = seekerRepository.findById(id);
		Seeker seeker = null;
		SeekerDTO seekerDTO = null;
		if(seekerOptional.isPresent()) {
			seeker = seekerOptional.get();

			seekerDTO = modelMapper.map(seeker, SeekerDTO.class);

			if (seekerDTO.getAvatar() != null && seekerDTO.getAvatar().startsWith("null")) {
				seekerDTO.setAvatar(seekerDTO.getAvatar().replace("null", ""));
			}
		}

		return seekerDTO;
	}

}
