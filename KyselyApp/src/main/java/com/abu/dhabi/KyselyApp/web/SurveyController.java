package com.abu.dhabi.KyselyApp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.abu.dhabi.KyselyApp.domain.OptionRepository;
import com.abu.dhabi.KyselyApp.domain.Question;
import com.abu.dhabi.KyselyApp.domain.QuestionRepository;
import com.abu.dhabi.KyselyApp.domain.QuestionType;
import com.abu.dhabi.KyselyApp.domain.Survey;
import com.abu.dhabi.KyselyApp.domain.SurveyRepository;
import com.abu.dhabi.KyselyApp.web.domain.AddQuestion;

@Controller
public class SurveyController {
	@Autowired
	private SurveyRepository surveyRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private OptionRepository optionRepository;
	


	@RequestMapping(value = "/addsurvey", method = RequestMethod.GET)
	public String addSurvey(Model model) {
		// Create and empty survey
		var survey = new Survey();
		model.addAttribute("survey", survey);
    	
		// Here we'll only ask for the name
		return "addsurvey";
	}
	
	@RequestMapping(value = "/editsurvey/{id}", method = RequestMethod.GET)
	public String editSurvey(@RequestParam(required = false) boolean saved, @PathVariable("id") Long id, Model model) {
		// Get the survey from corresponding to id from surveyRepository
		// Will throw an exception and crash if one doesn't exist with that id
		var survey = surveyRepository.findById(id).get();
		
		var addQuestion = new AddQuestion(survey.getId(), "text");

		model.addAttribute("survey_id", id);
		model.addAttribute("survey", survey);
		model.addAttribute("addQuestion", addQuestion);
		model.addAttribute("saved", saved);
		
		System.out.println("kysymykset: " + survey.getQuestions());
		return "editsurvey";
	}
	
	@RequestMapping(value = "/savesurvey", method = RequestMethod.POST)
    public String save(@ModelAttribute Survey survey){
		System.out.println(survey.getSurveyName());
		// Save the survey to the repository just in case
		var savedSurvey = surveyRepository.save(survey);
		

		// Loop through all of the questions
		// and save them to the repository
		if (survey.getQuestions() != null ) {
			for (var question : survey.getQuestions()) {
				question.setSurvey(savedSurvey);
				var savedQuestion = questionRepository.save(question);
				// If the question type has options save them as well
				// FIXME: This code could be arranged better
				if (QuestionType.hasOptions(savedQuestion.getType())) {
					for (var option : question.getOptions()) {
						System.out.println(option);
						optionRepository.save(option);
					}
				}
			}
		} else {
			var newQuestion = new Question(savedSurvey, "", QuestionType.Type.Text);
			questionRepository.save(newQuestion);
		}
		
		System.out.println(String.format("redirect:editsurvey/%d", savedSurvey.getId(), "?saved=true"));
		
		return String.format("redirect:editsurvey/%d?saved=true", savedSurvey.getId());

    }
	

	// Survey list where you can choose which survey you want to edit
	@RequestMapping(value = "/surveylist", method = RequestMethod.GET)
	public String surveyList(Model model) {
		List<Survey> surveys = (List<Survey>) surveyRepository.findAll();
		model.addAttribute("surveys", surveys);
		return "surveylist";
	}  
	
	
}
