package com.abu.dhabi.KyselyApp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abu.dhabi.KyselyApp.domain.Question;
import com.abu.dhabi.KyselyApp.domain.QuestionRepository;
import com.abu.dhabi.KyselyApp.domain.QuestionType;
import com.abu.dhabi.KyselyApp.domain.SurveyRepository;
import com.abu.dhabi.KyselyApp.web.domain.AddQuestion;

@Controller
public class QuestionController {
	@Autowired
	private SurveyRepository surveyRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	// Adding a question to the survey
	// Check the survey ID and and to the questionlist in the id
	@RequestMapping(value="/addquestion", method = RequestMethod.POST)
	public String addQuestion(@ModelAttribute AddQuestion addQuestion) {
		var survey = surveyRepository.findById(addQuestion.getId()).get();
		
		var questionType = QuestionType.fromString(addQuestion.getQuestionType());
		
		// We'll take the survey object from the form
		// Create new question for the survey and save it
		var newQuestion = new Question(survey, "", questionType);
		questionRepository.save(newQuestion);
		
		// Redirect the user back to the survey's edit page
		// The new empty question should appear on there now
		return String.format("redirect:/editsurvey/%d", survey.getId());
	}
	
	@RequestMapping(value="/deletequestion/{id}", method = RequestMethod.POST)
	public String deleteQuestion(@PathVariable("id") Long id) {
		//TODO: delete questions and everything linked to the questions
		
		// Redirect the user back to the survey's edit page
		// The new empty question should appear on there now
		//return String.format("redirect:/editsurvey/%d", survey.getId());
		return "yeet";
	}

}
