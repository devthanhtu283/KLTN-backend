package com.demo.configurations;

import com.demo.dtos.JobDTO;
import com.demo.dtos.ReviewDTO;
import com.demo.entities.*;

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
                map().setCategoryName(source.getCategory().getCategoryName());
                map().setEmployerLogo(source.getEmployer().getLogo());

            }

        });

        mapper.addMappings(new PropertyMap<Review, ReviewDTO>() {
            @Override
            protected void configure() {
                // TODO Auto-generated method stub
                map().setId(source.getId());
                map().setSeekerId(source.getSeeker().getId());
                map().setEmployerId(source.getEmployer().getId());
                map().setGoodMessage(source.getGoodMessage());
                map().setReason(source.getReason());
                map().setImprove(source.getImprove());
                map().setRating(source.getRating());
                map().setSatisfied(source.isSatisfied());
                map().setCreatedAt(source.getCreatedAt());
                map().setStatus(source.isStatus());
            }

        });


        return mapper;
    }

}