package com.abu.dhabi.KyselyApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.abu.dhabi.KyselyApp.domain.Answer;
import com.abu.dhabi.KyselyApp.domain.AnswerRepository;
import com.abu.dhabi.KyselyApp.domain.Option;
import com.abu.dhabi.KyselyApp.domain.OptionRepository;
import com.abu.dhabi.KyselyApp.domain.Question;
import com.abu.dhabi.KyselyApp.domain.QuestionRepository;
import com.abu.dhabi.KyselyApp.domain.QuestionType;
import com.abu.dhabi.KyselyApp.domain.Survey;
import com.abu.dhabi.KyselyApp.domain.SurveyRepository;


@SpringBootApplication
public class KyselyAppApplication {

	private static final Logger log = LoggerFactory.getLogger(KyselyAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KyselyAppApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner questionDemo(QuestionRepository qrepository, SurveyRepository srepository, AnswerRepository arepository, OptionRepository orepository) {
		return (args) -> {
			log.info("save surveys");

			Survey survey1 = new Survey("Asiakastyytyväisyyskysely", "Kerro mielipiteesi!");
			Survey survey2 = new Survey("Who are you?", "We'll find out with some simple questions.");

			srepository.save(survey1);
			srepository.save(survey2);
			
			Question question1 = new Question(survey1, "Kuinka usein vierailet ravintola Pääraiteessa?", QuestionType.Type.Radio);
			Question question2 = new Question(survey1, "Kuinka usein nautit aamiaisen ravintola Pääraiteessa?", QuestionType.Type.Radio);
			Question question3 = new Question(survey1, "Kuinka usein ostat lounaan yhteydessä myös jälkiruoan?", QuestionType.Type.Radio);
			Question question4 = new Question(survey1, "Miten tyytyväinen olet ravintolan aamiaiseen?", QuestionType.Type.Text);
			Question question5 = new Question(survey1, "Miten tyytyväinen olet ravintolan lounaaseen?", QuestionType.Type.Text);
			Question question6 = new Question(survey1, "Millaisia ruokia kaipaat lounasvalikoimaan?", QuestionType.Type.Text);
			Question question7 = new Question(survey1, "Miten hyvin ravintolassa on huomioitu eri ruokavaliot ja allergiat?", QuestionType.Type.Text);
			// Question question8 = new Question(survey1, "Kuinka usein ostat lounaan yhteydessä myös jälkiruoan?", QuestionType.Text);
			
			Question animalQ1 = new Question(survey2, "Are you a frog?", QuestionType.Type.Text);
			Question animalQ2 = new Question(survey2, "Are you a dog?", QuestionType.Type.Text);
			Question animalQ3 = new Question(survey2, "Are you a cat?", QuestionType.Type.Text);
			
			Option option1 = new Option(question1, "Kerran viikossa");
			Option option2 = new Option(question1, "Joka päivä");
			Option option3 = new Option(question1, "En koskaan");
			Option option4 = new Option(question2, "Kerran viikossa");
			Option option5 = new Option(question2, "Joka päivä");
			Option option6 = new Option(question2, "En koskaan");
			
			Answer answer4 = new Answer(animalQ1, "no");
			Answer answer5 = new Answer(animalQ2, "Woof");
			Answer answer6 = new Answer(animalQ3, "Meow?");
			Answer answer7 = new Answer(animalQ1, "yes");
			Answer answer8 = new Answer(animalQ2, "Woof");
			Answer answer9 = new Answer(animalQ3, "Meow?");
			Answer answer10 = new Answer(animalQ1, "Yes");
			
			
			
			
			qrepository.save(question1);
			qrepository.save(question2);
			qrepository.save(question3);
			qrepository.save(question4);
			qrepository.save(question5);
			qrepository.save(question6);
			qrepository.save(question7);
			qrepository.save(animalQ1);
			qrepository.save(animalQ2);
			qrepository.save(animalQ3);
			
		
			arepository.save(answer4);
			arepository.save(answer5);
			arepository.save(answer6);
			arepository.save(answer7);
			arepository.save(answer8);
			arepository.save(answer9);
			arepository.save(answer10);
			
			orepository.save(option1);
			orepository.save(option2);
			orepository.save(option3);
			orepository.save(option4);
			orepository.save(option5);
			orepository.save(option6);
			
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