package com.abu.dhabi.KyselyApp;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.abu.dhabi.KyselyApp.domain.Answer;
import com.abu.dhabi.KyselyApp.domain.AnswerRepository;
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
	public CommandLineRunner questionDemo(QuestionRepository qrepository, SurveyRepository srepository, AnswerRepository arepository) {
		return (args) -> {
			log.info("save surveys");

			Survey survey1 = new Survey("Asiakastyytyväisyys kysely");
			// Survey survey2 = new Survey("Second survey");

			srepository.save(survey1);
			// srepository.save(survey2);
			
			Question question1 = new Question(survey1, "Kuinka usein vierailet ravintola Pääraiteessa?", QuestionType.Text);
			Question question2 = new Question(survey1, "Kuinka usein nautit aamiaisen ravintola Pääraiteessa?", QuestionType.Text);
			Question question3 = new Question(survey1, "Kuinka usein ostat lounaan yhteydessä myös jälkiruoan?", QuestionType.Text);
			Question question4 = new Question(survey1, "Miten tyytyväinen olet ravintolan aamiaiseen?", QuestionType.Text);
			Question question5 = new Question(survey1, "Miten tyytyväinen olet ravintolan lounaaseen?", QuestionType.Text);
			Question question6 = new Question(survey1, "Millaisia ruokia kaipaat lounasvalikoimaan?", QuestionType.Text);
			Question question7 = new Question(survey1, "Miten hyvin ravintolassa on huomioitu eri ruokavaliot ja allergiat?", QuestionType.Text);
			// Question question8 = new Question(survey1, "Kuinka usein ostat lounaan yhteydessä myös jälkiruoan?", QuestionType.Text);
			/*
			Question question4 = new Question(survey2, "What's your favorite course in Haaga-Helia?", QuestionType.Text);
			Question question5 = new Question(survey2, "...", QuestionType.Text);
			Question question6 = new Question(survey2, "...", QuestionType.Text);
			*/
			
			Answer answer1 = new Answer(question1, "Yes");
			Answer answer2 = new Answer(question1, "No");
			Answer answer3 = new Answer(question1, "Maybe");
			
			Answer answer4 = new Answer(question3, "Woof");
			Answer answer5 = new Answer(question2, "Meow");
			Answer answer6 = new Answer(question2, "Ohjelmistoprojekti 1 of course :)");
			
			qrepository.save(question1);
			qrepository.save(question2);
			qrepository.save(question3);
			qrepository.save(question4);
			qrepository.save(question5);
			qrepository.save(question6);
			qrepository.save(question7);
			
			
			arepository.save(answer1);
			arepository.save(answer2);
			arepository.save(answer3);
			arepository.save(answer4);
			arepository.save(answer5);
			arepository.save(answer6);
			
			log.info("fetch all surveys");
			for (Survey survey : srepository.findAll()) {
				log.info(survey.toString());
			}

			log.info("fetch all questions");
			for (Question question : qrepository.findAll()) {
				log.info(question.toString());
			}
			
			log.info("fetch all answers");
			for (Answer answer : arepository.findAll()) {
				log.info(answer.toString());
			}
		};
	}
}