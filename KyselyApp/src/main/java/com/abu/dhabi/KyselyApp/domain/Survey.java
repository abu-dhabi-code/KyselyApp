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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	// private String surveyName;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "survey")
	@JsonIgnoreProperties("survey")
	private List<Question> questions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	public List<Question> getQuestions() {
		return this.questions;
	}
	
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	
}
