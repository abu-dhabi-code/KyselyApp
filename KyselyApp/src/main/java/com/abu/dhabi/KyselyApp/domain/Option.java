package com.abu.dhabi.KyselyApp.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Option {
	
	@Id
	@NonNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String option;
	
	@ManyToOne
	@JsonIgnoreProperties("options")
	@JoinColumn(name="questionid")
	@NonNull
	private Question question;
	
	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Option() {
		super();
	}
	
	public Option(String option) {
		super();
		this.option = option;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getOption() {
		return option;
	}


	public void setOption(String option) {
		this.option = option;
	}
	
}
