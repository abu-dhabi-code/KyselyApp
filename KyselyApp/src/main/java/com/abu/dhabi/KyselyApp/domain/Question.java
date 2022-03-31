package com.abu.dhabi.KyselyApp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String question;
	private Long surveyId;
	
	// Text is the default question type for now
	private QuestionType type = QuestionType.Text;

	public Question(Long surveyId, String question, QuestionType type) {
		super();
		this.surveyId = surveyId;
		this.question = question;
		this.type = type;
	}

	
	//getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	
	
	
	
}
