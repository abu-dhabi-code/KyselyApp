package com.abu.dhabi.KyselyApp.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

	//NOT TESTED!
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	        System.out.println(statusCode);
	        model.addAttribute("errorCode", statusCode);
	    
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            model.addAttribute("message", "Not Found");
	        }
	        else if(statusCode == HttpStatus.FORBIDDEN.value()) {
	            model.addAttribute("message", "Unauthorised");
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            model.addAttribute("message", "Server Error");
	        }
	        else {
	            model.addAttribute("message", "Error Occurred");
	        }
	    } 
	    else {
	        model.addAttribute("message", "Error Occurred");
	        model.addAttribute("errorCode", HttpStatus.BAD_REQUEST.value());
	    }
	    
	    return "error";
	}
}
