package com.abu.dhabi.KyselyApp.web.restcontrollers.v1;

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

import com.abu.dhabi.KyselyApp.domain.Answer;
import com.abu.dhabi.KyselyApp.domain.AnswerRepository;

@Controller
@RequestMapping("/api/v1")
public class AnswerRest {

	@Autowired
	private AnswerRepository arepository;
	
	// RESTful service to get all Answers
	@CrossOrigin
    @RequestMapping(value="/answers", method = RequestMethod.GET)
    public @ResponseBody List<Answer> AnswerListRest() {	
        return (List<Answer>) arepository.findAll();
    }
	
	// Answer-luokan olio etsitään id:n mukaan
	@CrossOrigin
	@RequestMapping(value="/answers/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Answer> findAnswerRest(@PathVariable("id") Long answerId) {	
    	return arepository.findById(answerId);
    }     
	
	// Ensimmäinen yritys vastauksen tallentamiseen
	@CrossOrigin
	@RequestMapping(value="/answers", method = RequestMethod.POST)
    public @ResponseBody Answer saveAnswerRest(@RequestBody Answer answer) {	
    	return arepository.save(answer);
    }
	
}
