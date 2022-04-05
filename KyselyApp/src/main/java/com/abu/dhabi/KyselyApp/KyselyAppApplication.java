package com.abu.dhabi.KyselyApp;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.abu.dhabi.KyselyApp.domain.Question;
import com.abu.dhabi.KyselyApp.domain.QuestionRepository;
import com.abu.dhabi.KyselyApp.domain.Survey;
import com.abu.dhabi.KyselyApp.domain.SurveyRepository;

import org.slf4j.Logger;


@SpringBootApplication
public class KyselyAppApplication {

	private static final Logger log = LoggerFactory.getLogger(KyselyAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KyselyAppApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner questionDemo(QuestionRepository qrepository, SurveyRepository srepository) {
		return (args) -> {
			log.info("save questions");
			qrepository.save(new Question());
			srepository.save(new Survey());
			
			log.info("fetch all surveys");
			for (Survey survey : srepository.findAll()) {
				log.info(survey.toString());
			}

			log.info("fetch all questions");
			for (Question question : qrepository.findAll()) {
				log.info(question.toString());
			}
};
}
}