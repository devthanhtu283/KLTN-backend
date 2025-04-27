package com.demo.services;


import com.demo.dtos.AnswerDTO1;
import com.demo.entities.Answer;
import com.demo.entities.Question;
import com.demo.repositories.AnswerRepository;
import com.demo.repositories.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public List<AnswerDTO1> createAnswers(List<AnswerDTO1> answerDTOs) {
        if (answerDTOs.size() > 4) {
            throw new RuntimeException("Maximum 4 answers allowed per question");
        }

        Integer questionId = answerDTOs.get(0).getQuestionId();
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        long correctAnswers = answerDTOs.stream().filter(AnswerDTO1::getIsCorrect).count();
        if (correctAnswers != 1) {
            throw new RuntimeException("Exactly one answer must be correct");
        }

        List<Answer> answers = answerDTOs.stream().map(dto -> {
            Answer answer = new Answer();
            answer.setQuestion(question);
            answer.setContent(dto.getContent());
            answer.setIsCorrect(dto.getIsCorrect());
            return answer;
        }).collect(Collectors.toList());

        List<Answer> savedAnswers = answerRepository.saveAll(answers);
        return savedAnswers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AnswerDTO1 convertToDTO(Answer answer) {
        AnswerDTO1 dto = new AnswerDTO1();
        dto.setId(answer.getId());
        dto.setQuestionId(answer.getQuestion().getId());
        dto.setContent(answer.getContent());
        dto.setIsCorrect(answer.getIsCorrect());
        return dto;
    }
}