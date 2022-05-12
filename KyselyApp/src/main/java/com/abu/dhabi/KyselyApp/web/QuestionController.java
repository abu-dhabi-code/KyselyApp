package com.abu.dhabi.KyselyApp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abu.dhabi.KyselyApp.domain.Answer;
import com.abu.dhabi.KyselyApp.domain.AnswerRepository;
import com.abu.dhabi.KyselyApp.domain.Option;
import com.abu.dhabi.KyselyApp.domain.OptionRepository;
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
	
	@Autowired
	private OptionRepository optionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	// Adding a question to the survey
	@RequestMapping(value="/addquestion", method = RequestMethod.POST)
	public String addQuestion(@ModelAttribute AddQuestion addQuestion) {
		var survey = surveyRepository.findById(addQuestion.getId()).get();
		
		var questionType = QuestionType.fromString(addQuestion.getQuestionType());	
		
		// We'll take the survey object from the form
		// Create new question for the survey and save it
		var newQuestion = new Question(survey, "", questionType);
		newQuestion = questionRepository.save(newQuestion);
		
		// If the created question is supposed to have options, we create them here
		// This is needed to stop options from being added to questions that don't use them
		if (QuestionType.hasOptions(newQuestion.getType())) {
			// Amount of options to add is gotten from the request object
			var optionCount = addQuestion.getOptionCount();
			
			if(optionCount == 0) {
				var newOption = new Option(newQuestion, "");
				optionRepository.save(newOption);
			} else {
				for (int i = 0; i < optionCount; i++) {
					var newOption = new Option(newQuestion, "");
					optionRepository.save(newOption);
				}	
			}
		}

		
		// Redirect the user back to the survey's edit page
		// The new empty question should appear on there now
		return String.format("redirect:/editsurvey/%d", survey.getId());
	}
	
	//deleting a question
	@RequestMapping(value="/deletequestion/{id}", method = RequestMethod.GET)
	public String deleteQuestion(@PathVariable("id") Long id) {
		var question = questionRepository.findById(id).get();
		var survey = question.getSurvey();
		
		//delete answers
		for (var answer : question.getAnswers()) {
			answerRepository.delete(answer);
		}
		//delete options
		if (QuestionType.hasOptions(question.getType())) {
			for (var option : question.getOptions()) {
				optionRepository.delete(option);
			}
		}
		//delete the question itself
		questionRepository.delete(question);
		
		return String.format("redirect:/editsurvey/%d", survey.getId());
	}

}
