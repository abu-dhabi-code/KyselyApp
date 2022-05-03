package com.abu.dhabi.KyselyApp.web.restcontrollers.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abu.dhabi.KyselyApp.domain.Survey;
import com.abu.dhabi.KyselyApp.domain.SurveyRepository;


@Controller
@RequestMapping("/api/v1")
public class SurveyRest {
	
	@Autowired
	private SurveyRepository srepository;
	
	// RESTful service to get all Surveys
    // Java-kielinen Survey-luokan oliolista muunnetaan JSON-listaksi ja 
    // lähetetään web-selaimelle vastauksena
	@CrossOrigin
    @RequestMapping(value="/surveys", method = RequestMethod.GET)
    public @ResponseBody List<Survey> surveyListRest() {
		var surveys = (List<Survey>) srepository.findAll();
		
		for (var survey : surveys) {
			for (var question : survey.getQuestions()) {
				question.setAnswers(null);
			}
		}
		return surveys;
    } 

	// RESTful service to get Survey by id without answers
	@CrossOrigin
    @RequestMapping(value="/surveys/{id}", method = RequestMethod.GET)
    public @ResponseBody Survey findSurveyRest(@PathVariable("id") Long SurveyId) {	
    	var survey = srepository.findById(SurveyId).get(); 
			for (var question : survey.getQuestions()) {
				question.setAnswers(null);
			}
		return survey;
    }    
	
	// RESTful service to get Survey by id with answers
	@CrossOrigin
	@RequestMapping(value="/surveys/{id}/answers", method = RequestMethod.GET)
	public @ResponseBody Optional<Survey> findSurveyRestAnswers(@PathVariable("id") Long surveyId) {	
		return srepository.findById(surveyId);
	}      
    
    // RESTful service to save new Survey
	@CrossOrigin
    @RequestMapping(value="/surveys", method = RequestMethod.POST)
    public @ResponseBody Survey saveSurveyRest(@RequestBody Survey survey) {	
    	return srepository.save(survey);
    }
	
	//REST Home Page
	@RequestMapping(value= "/resthome", method = RequestMethod.GET)
	public String RestHome(Model model){
	return "resthome";
	}
}
