package com.abu.dhabi.KyselyApp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abu.dhabi.KyselyApp.domain.Answer;
import com.abu.dhabi.KyselyApp.domain.AnswerRepository;
import com.abu.dhabi.KyselyApp.domain.Question;
import com.abu.dhabi.KyselyApp.domain.QuestionRepository;
import com.abu.dhabi.KyselyApp.domain.QuestionType;
import com.abu.dhabi.KyselyApp.domain.Survey;
import com.abu.dhabi.KyselyApp.domain.SurveyRepository;

@Controller
public class SurveyController {
	@Autowired
	private SurveyRepository surveyRepository;
	
	@Autowired
	private QuestionRepository questionRepository;

	@RequestMapping(value = "/addsurvey", method = RequestMethod.GET)
	public String addSurvey(Model model) {
		// Create and empty survey
		var survey = new Survey();
		model.addAttribute("survey", survey);
    	
		// Here we'll only ask for the name
		return "addsurvey";
	}
	
	@RequestMapping(value = "/editsurvey/{id}", method = RequestMethod.GET)
	public String editSurvey(@PathVariable("id") Long id, Model model) {
		// Get the survey from corresponding to id from surveyRepository
		// Will throw an exception and crash if one doesn't exist with that id
		var survey = surveyRepository.findById(id).get();
		

		model.addAttribute("survey_id", id);
		model.addAttribute("survey", survey);
		
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
			for (var question : survey.getQuestions())
				questionRepository.save(question);
		} else {
			var newQuestion = new Question(savedSurvey, "", QuestionType.Text);
			questionRepository.save(newQuestion);
		}
		
		return String.format("redirect:editsurvey/%d", savedSurvey.getId());

    }
	
	// Adding a question to the survey
	// Check the survey ID and and to the questionlist in the id
	@RequestMapping(value="/addquestion", method = RequestMethod.POST)
	public String addQuestion(@ModelAttribute Survey survey) {
		// We'll take the survey object from the form
		// Create new question for the survey and save it
		var newQuestion = new Question(survey, "", QuestionType.Text);
		questionRepository.save(newQuestion);
		
		// Redirect the user back to the survey's edit page
		// The new empty question should appear on there now
		return String.format("redirect:/editsurvey/%d", survey.getId());
	}
	
	// Survey list where you can choose which survey you want to edit
	@RequestMapping(value = "/surveylist", method = RequestMethod.GET)
	public String surveyList(Model model) {
		List<Survey> surveys = (List<Survey>) surveyRepository.findAll();
		model.addAttribute("surveys", surveys);
		return "surveylist";
	}  
}
