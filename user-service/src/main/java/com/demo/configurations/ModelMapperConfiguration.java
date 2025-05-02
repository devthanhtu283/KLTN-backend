package com.demo.configurations;


import java.sql.Date;

import com.demo.entities.*;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.demo.dtos.EmployerDTO;
import com.demo.dtos.SeekerDTO;
import com.demo.dtos.UserDTO;


@Configuration
public class ModelMapperConfiguration {
    @Autowired
    private Environment environment;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        Converter<String, String> converterPhotoToUrl = new AbstractConverter<String, String>() {

            @Override
            protected String convert(String source) {
                return environment.getProperty("IMAGES_URL") + source;
            }

        };

        mapper.getConfiguration().setSkipNullEnabled(true);

        //convert typeQuestion
        Converter<Integer, String> convertTypeQuestion = new AbstractConverter<Integer, String>() {
            @Override
            protected String convert(Integer source) {
                String typeQuestion = "";
                if (source == 1) {
                    typeQuestion = "choiceAnswer";
                } else if (source == 2) {
                    typeQuestion = "textAnswer";
                }
                return typeQuestion;

            }

        };

        mapper.typeMap(Seeker.class, SeekerDTO.class).addMappings(m -> {
            m.using(converterPhotoToUrl).map(Seeker::getAvatar, SeekerDTO::setAvatar);
        });


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


        // mapper cho Seeker
        mapper.addMappings(new PropertyMap<Seeker, SeekerDTO>() {

            @Override
            protected void configure() {
                // TODO Auto-generated method stub

            }

        });

        // mapper cho employer
        mapper.addMappings(new PropertyMap<Employer, EmployerDTO>() {

            @Override
            protected void configure() {
                // TODO Auto-generated method stub
                map().setId(source.getId());
                map().setCompanyName(source.getCompanyName());
                map().setCompanyProfile(source.getCompanyProfile());
                map().setRating(source.getRating());
                map().setContactInfo(source.getContactInfo());
                map().setLogo(source.getLogo());
                map().setCoverImg(source.getCoverImg());
                map().setAddress(source.getAddress());
                map().setMapLink(source.getMapLink());
                map().setAmount(source.getAmount());
                map().setStatus(source.getStatus());
                map().setDescription(source.getDescription());
                map().setFoundedIn(source.getFoundedIn());
                map().setTeamMember(source.getTeamMember());
                map().setCompanyField(source.getCompanyField());
                map().setCompanyLink(source.getCompanyLink());
            }
        });

        // mapper cho USERDTO
        mapper.addMappings(new PropertyMap<UserDTO, User>() {

            @Override
            protected void configure() {
                // TODO Auto-generated method stub
                map().setId(source.getId());
                map().setUsername(source.getUsername());
                map().setEmail(source.getEmail());
                map().setPassword(source.getPassword());
                map().setUserType(source.getUserType());
                map().setSecurityCode(source.getSecurityCode());
                map().setStatus(source.isStatus());
            }

        });

//	
//		
//		//mapper cho ANSWER
//		 mapper.addMappings(new PropertyMap<Answer, AnswerDTO>() {
//		        @Override
//		        protected void configure() {
//		            map().setId(source.getId());
//		            map().setContent(source.getContent());
//		            map().setCorrect(source.getIsCorrect());
//		            map().setQuestionID(source.getQuestion().getId());
//		        }
//		    });
        return mapper;
    }
}