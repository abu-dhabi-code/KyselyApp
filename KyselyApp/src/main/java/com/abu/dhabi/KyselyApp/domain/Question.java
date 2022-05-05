package com.abu.dhabi.KyselyApp.domain;

import java.util.List;

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
public class Question {

	@Id
	@NonNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToOne
	@JsonIgnoreProperties("questions")
    @JoinColumn(name = "surveyid")
	@NonNull
	private Survey survey;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
	@JsonIgnoreProperties("question")
	private List<Answer> answers;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Option> options;
	
	public Question() {
		super();
	}

	// Text is the default question type for now
	private QuestionType.Type type = QuestionType.Type.Text;

	public Question(Survey survey, String question, QuestionType.Type type) {
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

	public QuestionType.Type getType() {
		return type;
	}

	public void setType(QuestionType.Type type) {
		this.type = type;
	}
	
	public List<Answer> getAnswers() {
		return this.answers;
	}
	
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public List<Option> getOptions() {
		return this.options;
	}
	
	public void setOptions(List<Option> options) {
		this.options = options;
	}


	@Override
	public String toString() {
		return "Question [id=" + id + ", name=" + name + ", survey=" + survey.getSurveyName() + ", type=" + type + "]";
	}
	
	
}
