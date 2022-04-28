package com.abu.dhabi.KyselyApp.web.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class AddQuestion {
	@Id
	private Long _id;
	
	@JsonProperty("id")
	private Long id;
	@JsonProperty("questionType")
	private String questionType;
	
	
	
	public AddQuestion(Long id, String questionType) {
		super();
		this.id = id;
		this.questionType = questionType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public AddQuestion() {
		super();
	}
	
}
