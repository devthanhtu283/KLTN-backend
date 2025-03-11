package com.demo.configurations;


import com.demo.dto.ApplicationDTO;
import com.demo.entities.Application;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;




@Configuration
public class ModelMapperConfiguration {
	@Autowired
	private Environment environment;

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();


		mapper.addMappings(new PropertyMap<Application, ApplicationDTO>() {
			@Override
			protected void configure() {
				// TODO Auto-generated method stub
				map().setId(source.getId());
				map().setSeekerId(source.getSeeker().getId());
				map().setSeekerName(source.getSeeker().getFullName());
				map().setAddress(source.getSeeker().getAddress());
				map().setAvatar(source.getSeeker().getAvatar());
				map().setPhone(source.getSeeker().getPhone());
				map().setJobId(source.getJob().getId());
				map().setJobTitle(source.getJob().getTitle());
				map().setStatus(source.getStatus());
			}

		});

		mapper.addMappings(new PropertyMap<ApplicationDTO, Application>() {
			@Override
			protected void configure() {
				// TODO Auto-generated method stub
				map().setId( source.getId());
				map().setAppliedAt(source.getAppliedAt());
				map().setStatus(source.getStatus());
			}

		});


		return mapper;
	}
}