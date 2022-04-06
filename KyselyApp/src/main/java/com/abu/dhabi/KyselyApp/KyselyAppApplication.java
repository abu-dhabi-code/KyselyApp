package com.abu.dhabi.KyselyApp;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.abu.dhabi.KyselyApp.domain.Question;
import com.abu.dhabi.KyselyApp.domain.QuestionRepository;
import com.abu.dhabi.KyselyApp.domain.QuestionType;
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
			log.info("save surveys");
			Survey survey1 = new Survey("First survey");
			Survey survey2 = new Survey("Second survey");
			srepository.save(survey1);
			srepository.save(survey2);
			
			Question question1 = new Question(survey1, "Are you a frog?", QuestionType.Text);
			Question question2 = new Question(survey2, "What's your favorite course in Haaga-Helia?", QuestionType.Text);
			Question question3 = new Question(survey1, "Are you a dog?", QuestionType.Text);
			Question question4 = new Question(survey1, "Are you a cat?", QuestionType.Text);
			qrepository.save(question1);
			qrepository.save(question2);
			qrepository.save(question3);
			qrepository.save(question4);
			
			
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