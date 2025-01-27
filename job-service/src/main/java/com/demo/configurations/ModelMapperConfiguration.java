package com.demo.configurations;
import com.demo.dtos.JobDTO;
import com.demo.entities.Job;
import com.demo.entities.Location;
import com.demo.entities.Question;
import com.demo.entities.Test;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
public class ModelMapperConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();

		mapper.addMappings(new PropertyMap<Job, JobDTO>() {
			@Override
			protected void configure() {
				// TODO Auto-generated method stub
				map().setExperienceName(source.getExperience().getName());
				map().setEmployerName(source.getEmployer().getCompanyName());
				map().setLocationName(source.getLocation().getName());
				map().setWorktypeName(source.getWorktype().getName());

			}

		});

		return mapper;
	}

}