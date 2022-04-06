package com.abu.dhabi.KyselyApp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String addSurvey(final RedirectAttributes redirectAttributes) {
		//new empty survey
		var newSurvey = new Survey("Survey name..");
		surveyRepository.save(newSurvey);
		redirectAttributes.addFlashAttribute("survey", newSurvey);
    	
		return String.format("redirect:editsurvey/%d", newSurvey.getId());
	}
	
	@RequestMapping(value = "/editsurvey/{id}", method = RequestMethod.GET)
	public String editSurvey(
			@PathVariable("id") Long id, 
		    @ModelAttribute("survey") final Survey survey, 
		    final RedirectAttributes redirectAttributes,	// ADDING REDIRECT ATTRIBUTES
		    Model model) {
		
		/*System.out.println("Onko NULL (EDIT)" + survey.getId());
		System.out.println(id);
		var questionList = survey.getQuestions();
		Question newQuestion = null;
		if (questionList == null || questionList.isEmpty()) {
			System.out.println("New question created");
			newQuestion = new Question(survey, "", QuestionType.Text);
			questionRepository.save(newQuestion);
		} else {
			System.out.println("Get old question");
			newQuestion = questionList.get(0);
		}
		model.addAttribute("question", newQuestion);*/
		var kysely = surveyRepository.findById(id).orElse(null);
		/*System.out.println("UUDEN QUESTIONIN ID: " + newQuestion.getId());
		System.out.println("SURVEY ID: " + newQuestion.getSurvey().getId());*/
		model.addAttribute("survey_id", id);
		model.addAttribute("survey", kysely);
		System.out.println("kysymykset: " + survey.getQuestions());
		//redirectAttributes.addFlashAttribute("survey_id", id);	// REDIRECTING EDITED SURVEY TO "survey"
		return "addsurvey";
	}
	
	@RequestMapping(value = "/savesurvey", method = RequestMethod.POST)
    public String save(@ModelAttribute Survey survey){
		System.out.println(survey.getSurveyName());
		//System.out.println(survey.getSurvey().getId());
		//System.out.println(survey.getType());
        //srepository.save(survey);
		surveyRepository.save(survey);
		
		for (var question : survey.getQuestions())
			questionRepository.save(question);
		
		return String.format("redirect:editsurvey/%d", survey.getId());
    }
	
	// Adding a question to the survey
	// Check the survey ID and and to the questionlist in the id
	@RequestMapping(value="/addquestion/{id}", method = RequestMethod.POST)
	public String addQuestion(@PathVariable("id") Long id) {
		var survey = surveyRepository.findById(id).get();
		
		var newQuestion = new Question(survey, "", QuestionType.Text);	// CREATE A NEW QUESTION
		questionRepository.save(newQuestion);
		
		return String.format("redirect:/editsurvey/%d", survey.getId());
	}
	
	//survey list where you can choose which survey you want to edit
	@RequestMapping(value = "/surveylist", method = RequestMethod.GET)
	public String surveyList(Model model) {
		List<Survey> surveys = (List<Survey>) surveyRepository.findAll();
		model.addAttribute("surveys", surveys);
		return "surveylist";
	}
}
