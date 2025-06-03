package com.demo.configurations;

import com.demo.dtos.*;
import com.demo.entities.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        Converter<String, JsonNode> stringToJsonNode = ctx -> {
            if (ctx.getSource() == null) return null;
            try {
                ObjectMapper map = new ObjectMapper();
                return map.readTree(ctx.getSource());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        };

        // mapper cho USER
        mapper.addMappings(new PropertyMap<User, UserDTO>() {

            @Override
            protected void configure() {
                // TODO Auto-generated method stub
                map().setId(source.getId());
                map().setUsername(source.getUsername());
                map().setEmail(source.getEmail());
                map().setPassword(source.getPassword());
                map().setUserType(source.getUserType());

                map().setSecurityCode(source.getSecurityCode());
                map().setStatus(source.getStatus());
            }

        });


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
                using(stringToJsonNode).map(source.getDescriptionJson()).setDescriptionJson(null);

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

        mapper.addMappings(new PropertyMap<Notification, NotificationDTO>() {
            @Override
            protected void configure() {
                // TODO Auto-generated method stub
                map().setId(source.getId());
                map().setUserID(source.getUser().getId());
                map().setContent(source.getContent());
                map().setRead(source.isRead());
                map().setType(source.getType());
                map().setTitle(source.getTitle());
                map().setCreatedAt(source.getCreatedAt());
            }

        });

        mapper.addMappings(new PropertyMap<Follow, FollowDTO>() {
            @Override
            protected void configure() {
                // TODO Auto-generated method stub
                map().setId(source.getId());
                map().setSeekerId(source.getSeeker().getId());
                map().setEmployerId(source.getEmployer().getId());
                map().setAddress(source.getSeeker().getAddress());
                map().setCreated(source.getCreated());
                map().setEmployerName(source.getEmployer().getCompanyName());
                map().setSeekerName(source.getSeeker().getFullName());
                map().setLogo(source.getEmployer().getLogo());
                map().setStatus(source.isStatus());
            }

        });

        mapper.addMappings(new PropertyMap<Matches, MatchesDTO>() {
            @Override
            protected void configure() {
                // Map các trường cơ bản của Matches
                map().setId(source.getId());
                map().setCvId(source.getCv().getId());
                map().setSeekerId(source.getCv().getSeeker().getId());
                map().setMatchedSkill(source.getMatchedSkill());
                map().setTimeMatches(source.getTimeMatches());
                map().setStatus(source.isStatus());

                // Map các trường từ Job
                map().setJobId(source.getJob().getId());
                map().setJobName(source.getJob().getTitle());
                map().setEmployerId(source.getJob().getEmployer().getId());
                map().setExperienceId(source.getJob().getExperience().getId());
                map().setLocationId(source.getJob().getLocation().getId());
                map().setWorktypeId(source.getJob().getWorktype().getId());
                map().setCategoryId(source.getJob().getCategory().getId());
                map().setEmployerName(source.getJob().getEmployer().getCompanyName());
                map().setExperienceName(source.getJob().getExperience().getName());
                map().setEmployerLogo(source.getJob().getEmployer().getLogo());
                map().setLocationName(source.getJob().getLocation().getName());
                map().setWorktypeName(source.getJob().getWorktype().getName());
                map().setCategoryName(source.getJob().getCategory().getCategoryName());
                map().setTitle(source.getJob().getTitle());
                map().setDescription(source.getJob().getDescription());
                map().setRequired(source.getJob().getRequired());
                map().setAddress(source.getJob().getAddress());
                map().setSalary(source.getJob().getSalary());
                map().setPostedAt(source.getJob().getPostedAt());
                map().setPostedExpired(source.getJob().getPostedExpired());
                map().setRequiredSkills(source.getJob().getRequiredSkills());
                map().setMember(source.getJob().getMember());
            }
        });


        return mapper;
    }

}