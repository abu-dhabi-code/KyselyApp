package com.abu.dhabi.KyselyApp.domain;

public class Question {

	private int id;
	private String question;
	
	// Text is the default question type for now
	private QuestionType type = QuestionType.Text;

	public Question(int id, String question, QuestionType type) {
		super();
		this.id = id;
		this.question = question;
		this.type = type;
	}

	
	
	
	
}
