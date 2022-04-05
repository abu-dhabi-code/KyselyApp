package com.abu.dhabi.KyselyApp.web;

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
			@PathVariable String id, 
		    @ModelAttribute("survey") final Survey survey, 
		    final RedirectAttributes redirectAttributes,	// ADDING REDIRECT ATTRIBUTES
		    Model model) {

		Long id_long = Long.parseLong(id);
		System.out.println(id_long);
		var questionList = survey.getQuestions();
		if (questionList == null || questionList.isEmpty()) {
			var newQuestion = new Question(survey, "", QuestionType.Text);
			model.addAttribute("question", newQuestion);
		} else {
			model.addAttribute("question", questionList.get(0));
		}
		model.addAttribute("survey_id", id);
		redirectAttributes.addFlashAttribute("survey", survey);	// 
		return "addsurvey";
	}
	
	@RequestMapping(value = "/savesurvey", method = RequestMethod.POST)
    public String save(@ModelAttribute Question question){
		System.out.println(question.getName());
		System.out.println(question.getSurvey().getId());
		System.out.println(question.getType());
        //srepository.save(survey);
		questionRepository.save(question);
		return String.format("redirect:editsurvey/%d", question.getSurvey().getId());
    }
	
	// Adding a question to the survey
	// Check the survey ID and and to the questionlist in the id
	@RequestMapping(value="/addquestion", method = RequestMethod.POST)
	public String addQuestion(@ModelAttribute Survey survey) {
		var newQuestion = new Question(survey, "", QuestionType.Text);	// CREATE A NEW QUESTION
		return String.format("redirect:editsurvey/%d", newQuestion.getSurvey().getId());
	}
}
