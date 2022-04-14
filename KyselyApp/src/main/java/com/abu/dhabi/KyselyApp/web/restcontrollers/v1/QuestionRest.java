package com.abu.dhabi.KyselyApp.web.restcontrollers.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abu.dhabi.KyselyApp.domain.Question;
import com.abu.dhabi.KyselyApp.domain.QuestionRepository;

@Controller
@RequestMapping("/api/v1")
public class QuestionRest {

	@Autowired
	private QuestionRepository qrepository;
	
	// RESTful service to get all Answers
	@CrossOrigin
    @RequestMapping(value="/questions", method = RequestMethod.GET)
    public @ResponseBody List<Question> QuestionListRest() {	
        return (List<Question>) qrepository.findAll();
    } 
}
