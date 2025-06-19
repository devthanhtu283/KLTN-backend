package com.demo.configurations;


import com.demo.dtos.ApplicationDTO;
import com.demo.dtos.InterviewDTO;
import com.demo.entities.Application;
import com.demo.entities.Interview;
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
                map().setAddress(source.getJob().getAddress());
                map().setAvatar(source.getJob().getEmployer().getLogo());
                map().setPhone(source.getSeeker().getPhone());
                map().setJobId(source.getJob().getId());
                map().setJobTitle(source.getJob().getTitle());
                map().setWorkType(source.getJob().getWorktype().getName());
                map().setStatus(source.getStatus());
                map().setExperience(source.getJob().getExperience().getName());
                map().setSalary(source.getJob().getSalary());
                map().setCompanyName(source.getJob().getEmployer().getCompanyName());
            }

        });

        mapper.addMappings(new PropertyMap<Interview, InterviewDTO>() {
            @Override
            protected void configure() {
                // TODO Auto-generated method stub
                map().setId(source.getId());
                map().setApplicationId(source.getApplication().getId());
                map().setScheduledAt(source.getScheduledAt());
                map().setInterviewLink(source.getInterviewLink());
                map().setStatus(source.getStatus());
                map().setJobTitle(source.getApplication().getJob().getTitle());
                map().setSeekerName(source.getApplication().getSeeker().getFullName());
                map().setEmployeeName(source.getApplication().getJob().getEmployer().getCompanyName());
            }
        });

        mapper.addMappings(new PropertyMap<ApplicationDTO, Application>() {
            @Override
            protected void configure() {
                // TODO Auto-generated method stub
                map().setId(source.getId());
                map().setAppliedAt(source.getAppliedAt());
                map().setStatus(source.getStatus());
            }

        });


        return mapper;
    }
}