package com.abu.dhabi.KyselyApp.domain;


public final class QuestionType {
	// Enumerating different question types (ex. text = 0, multiple-choice = 1)
	public static enum Type{
		Text,
		LongText,
		Radio,
		Multiselect,
	}
	
	public static Type fromString(String name) {
		switch(name) {
		case "text":
			return Type.Text;
		case "longtext":
			return Type.LongText;
		case "radio":
			return Type.Radio;
		case "multiselect":
			return Type.Multiselect;
		default:
			return Type.Text;	
		}
	}
}
