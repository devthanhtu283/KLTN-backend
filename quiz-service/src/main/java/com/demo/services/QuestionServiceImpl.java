package com.demo.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.demo.dtos.AnswerDTO1;
import com.demo.dtos.QuestionDTO1;
import com.demo.entities.Answer;
import com.demo.entities.Question;
import com.demo.entities.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dtos.QuestionDTO;
import com.demo.repositories.QuestionRepository;
import com.demo.repositories.TestRepository;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private TestRepository testRepository;

	@Override
	public List<QuestionDTO> findByTestID(int testID) {
		return mapper.map(questionRepository.findQuestionByTestID(testID), new TypeToken<List<QuestionDTO>>() {}.getType());
	}

	@Override
	public QuestionDTO1 createQuestion(QuestionDTO1 questionDTO) {
		// Validate answers
		if (questionDTO.getAnswers() != null) {
			long correctAnswers = questionDTO.getAnswers().stream().filter(AnswerDTO1::getIsCorrect).count();
			if (correctAnswers != 1) {
				throw new RuntimeException("Chỉ được phép có đúng một câu trả lời đúng");
			}
			if (questionDTO.getAnswers().size() > 4) {
				throw new RuntimeException("Tối đa 4 câu trả lời được phép");
			}
		}

		Test test = testRepository.findById(questionDTO.getTestId())
				.orElseThrow(() -> new RuntimeException("Không tìm thấy bài test"));

		Question question = new Question();
		question.setTest(test);
		question.setQuestionType(questionDTO.getQuestionType());
		question.setContent(questionDTO.getContent());

		// Khởi tạo danh sách answers
		Set<Answer> answers = new HashSet<>();
		if (questionDTO.getAnswers() != null && !questionDTO.getAnswers().isEmpty()) {
			answers = questionDTO.getAnswers().stream().map(answerDTO -> {
				Answer answer = new Answer();
				answer.setQuestion(question);
				answer.setContent(answerDTO.getContent());
				answer.setIsCorrect(answerDTO.getIsCorrect());
				return answer;
			}).collect(Collectors.toSet());
		}

		question.setAnswers(answers);
		Question savedQuestion = questionRepository.save(question);
		return convertToDTO(savedQuestion);
	}

	@Override
	public QuestionDTO1 updateQuestion(QuestionDTO1 questionDTO) {
		Question question = questionRepository.findById(questionDTO.getId())
				.orElseThrow(() -> new RuntimeException("Question not found"));

		// Validate answers
		if (questionDTO.getAnswers() != null) {
			long correctAnswers = questionDTO.getAnswers().stream().filter(AnswerDTO1::getIsCorrect).count();
			if (correctAnswers != 1) {
				throw new RuntimeException("Chỉ được phép có đúng một câu trả lời đúng");
			}
			if (questionDTO.getAnswers().size() > 4) {
				throw new RuntimeException("Tối đa 4 câu trả lời được phép");
			}
		}

		Test test = testRepository.findById(questionDTO.getTestId())
				.orElseThrow(() -> new RuntimeException("Không tìm thấy bài test"));

		question.setTest(test);
		question.setQuestionType(questionDTO.getQuestionType());
		question.setContent(questionDTO.getContent());

		// Clear existing answers and add new ones
		question.getAnswers().clear();
		Set<Answer> answers = new HashSet<>();
		if (questionDTO.getAnswers() != null && !questionDTO.getAnswers().isEmpty()) {
			answers = questionDTO.getAnswers().stream().map(answerDTO -> {
				Answer answer = new Answer();
				answer.setId(answerDTO.getId()); // Preserve existing answer ID if present
				answer.setQuestion(question);
				answer.setContent(answerDTO.getContent());
				answer.setIsCorrect(answerDTO.getIsCorrect());
				return answer;
			}).collect(Collectors.toSet());
		}
		question.setAnswers(answers);

		Question updatedQuestion = questionRepository.save(question);
		return convertToDTO(updatedQuestion);
	}

	@Override
	public void deleteQuestion(Integer id) {
		Question question = questionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Question not found"));
		questionRepository.delete(question);
	}

	private QuestionDTO1 convertToDTO(Question question) {
		QuestionDTO1 dto = new QuestionDTO1();
		dto.setId(question.getId());
		dto.setTestId(question.getTest().getId());
		dto.setQuestionType(question.getQuestionType());
		dto.setContent(question.getContent());
		dto.setAnswers(question.getAnswers().stream().map(answer -> {
			AnswerDTO1 answerDTO = new AnswerDTO1();
			answerDTO.setId(answer.getId());
			answerDTO.setQuestionId(answer.getQuestion().getId());
			answerDTO.setContent(answer.getContent());
			answerDTO.setIsCorrect(answer.getIsCorrect());
			return answerDTO;
		}).collect(Collectors.toSet()));
		return dto;
	}
}