package com.demo.configurations;


import com.demo.entities.Question;
import com.demo.entities.Test;
import com.demo.repositories.UserRepository;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.demo.dtos.QuestionDTO;
import com.demo.dtos.TestDTO;


@Configuration
public class ModelMapperConfiguration {
	@Autowired
	private Environment environment;
	@Autowired
	private UserRepository userRepository;
	

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		
		//convert typeQuestion
		Converter<Integer, String> convertTypeQuestion = new AbstractConverter<Integer, String>() {
			@Override
			protected String convert(Integer source) {
				String typeQuestion = "";
				if(source == 1) {
					typeQuestion = "choiceAnswer";
				} else if(source == 2) {
					typeQuestion = "textAnswer";
				}
				return typeQuestion;
		
			}
		
		};
		// mapper cho TEST
		mapper.addMappings(new PropertyMap<Test, TestDTO>() {
			@Override
			protected void configure() {
				// TODO Auto-generated method stub
				map().setUsername(source.getUser().getEmployer().getCompanyName());

			}
			
		});
		

		//mapper cho QUESTION
		mapper.addMappings(new PropertyMap<Question, QuestionDTO>() {
			@Override
			protected void configure() {
				// TODO Auto-generated method stub
				 using(convertTypeQuestion).map(source.getQuestionType(), destination.getQuestionType());
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