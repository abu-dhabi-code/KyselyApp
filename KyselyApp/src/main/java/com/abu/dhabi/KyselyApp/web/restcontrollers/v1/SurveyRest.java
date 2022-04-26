package com.abu.dhabi.KyselyApp.web.restcontrollers.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abu.dhabi.KyselyApp.domain.Question;
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

	// RESTful service to get Survey by id
	@CrossOrigin
    @RequestMapping(value="/surveys/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Survey> findSurveyRest(@PathVariable("id") Long SurveyId) {	
    	return srepository.findById(SurveyId);
    }      
    
    // RESTful service to save new Survey
	@CrossOrigin
    @RequestMapping(value="/surveys", method = RequestMethod.POST)
    public @ResponseBody Survey saveSurveyRest(@RequestBody Survey Survey) {	
    	return srepository.save(Survey);
    }
	
	//REST Home Page
    
}
