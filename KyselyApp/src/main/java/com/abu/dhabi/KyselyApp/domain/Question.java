package com.abu.dhabi.KyselyApp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	
	@ManyToOne
	@JsonIgnoreProperties("questions")
    @JoinColumn(name = "surveyid")
	@NonNull
	private Survey survey;
	
	public Question() {
		super();
	}

	// Text is the default question type for now
	private QuestionType type = QuestionType.Text;

	public Question(Survey survey, String question, QuestionType type) {
		super();
		this.survey = survey;
		this.name = question;
		this.type = type;
	}

	
	//getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	
	
	
	
}
