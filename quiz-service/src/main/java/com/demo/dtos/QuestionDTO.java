package com.demo.dtos;

import java.util.List;

public class QuestionDTO {
	private int id;
	private int testID;
	private String questionType;
	private String content;
	private List<AnswerDTO> answers;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTestID() {
		return testID;
	}
	public void setTestID(int testID) {
		this.testID = testID;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<AnswerDTO> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerDTO> answers) {
		this.answers = answers;
	}
	
	
	
}
