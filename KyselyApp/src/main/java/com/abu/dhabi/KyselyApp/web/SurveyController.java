package com.abu.dhabi.KyselyApp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SurveyController {

	@RequestMapping(value = "/addsurvey", method = RequestMethod.GET)
	public String addSurvey(Model model) {
		//new empty survey
		//model.addAttribute("newsurvey", new Survey());
    	
		return "addsurvey";
	}
}
