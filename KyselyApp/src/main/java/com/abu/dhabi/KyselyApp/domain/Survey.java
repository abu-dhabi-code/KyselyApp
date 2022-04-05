package com.abu.dhabi.KyselyApp.domain;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Survey {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String surveyName;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "survey")
	@JsonIgnoreProperties("survey")
	private List<Question> questions;
	
	//constructors
	public Survey() {
		super();
	}
	
	public Survey(String surveyName) {
		super();
		this.surveyName = surveyName;
	}

	//getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}
	
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Survey [id=" + id + ", surveyName=" + surveyName + "]";
	}
	
	
}
