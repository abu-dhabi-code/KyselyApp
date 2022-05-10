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
public class Answer {
	
	@Id
	@NonNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String answer;
	
	@ManyToOne
	@JsonIgnoreProperties("answers")
	@JoinColumn(name="questionid")
	@NonNull
	private Question question;
	
	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Answer(Question question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
	}

	public Long getId() {
		return id;
	}

	public String getAnswer() {
		return answer;
	}

	public Question getQuestion() {
		return question;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", answer=" + answer + ", question=" + question + "]";
	}
	
	
}
