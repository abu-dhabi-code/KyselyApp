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
			Question question4 = new Question(survey1, "Miten tyytyväinen olet ravintolan aamiaiseen?", QuestionType.Type.Radio);
			Question question5 = new Question(survey1, "Miten tyytyväinen olet ravintolan lounaaseen?", QuestionType.Type.Radio);
			Question question6 = new Question(survey1, "Millaisia ruokia kaipaat lounasvalikoimaan?", QuestionType.Type.Text);
			//Question question66 = new Question(survey1, "?", QuestionType.Type.Multiselect);
			Question question7 = new Question(survey1, "Miten hyvin ravintolassa on huomioitu eri ruokavaliot ja allergiat?", QuestionType.Type.Radio);
			Question question8 = new Question(survey1, "Mistä syistä vierailet ravintola Pääraiteessa?", QuestionType.Type.Multiselect);
			Question question9 = new Question(survey1, "Kerro vapaasti palautetta ravintolan toiminnasta :)", QuestionType.Type.LongText);
			
			Question animalQ1 = new Question(survey2, "Are you a frog?", QuestionType.Type.Text);
			Question animalQ2 = new Question(survey2, "Are you a dog?", QuestionType.Type.Text);
			Question animalQ3 = new Question(survey2, "Are you a cat?", QuestionType.Type.Text);
			
			//Vastausvaihtoehdot radiokysymyksiin
			Option option1 = new Option(question1, "Joka päivä");
			Option option2 = new Option(question1, "Muutaman kerran viikossa");
			Option option3 = new Option(question1, "Kerran viikossa");
			Option option4 = new Option(question1, "En koskaan");
			Option option5 = new Option(question2, "Joka päivä");
			Option option6 = new Option(question2, "Muutaman kerran viikossa");
			Option option7 = new Option(question2, "Kerran viikossa");
			Option option8 = new Option(question2, "En koskaan");
			Option option9 = new Option(question3, "Aina kun sitä on tarjolla");
			Option option10 = new Option(question3, "Muutaman kerran viikossa");
			Option option11 = new Option(question3, "Kerran viikossa");
			Option option12 = new Option(question3, "En koskaan");
			Option option13 = new Option(question4, "Erittäin tyytyväinen");
			Option option14 = new Option(question4, "Melko tyytyväinen");
			Option option15 = new Option(question4, "En kovin tyytyväinen");
			Option option16 = new Option(question5, "Erittäin tyytyväinen");
			Option option17 = new Option(question5, "Melko tyytyväinen");
			Option option18 = new Option(question5, "En kovin tyytyväinen");
			Option option19 = new Option(question7, "Erittäin hyvin");
			Option option20 = new Option(question7, "Melko hyvin");
			Option option21 = new Option(question7, "Kohtalaisesti");
			Option option22 = new Option(question7, "Huonosti");
			Option option23 = new Option(question7, "Ei ollenkaan");
			Option option24 = new Option(question8, "Ruoka on halpaa");
			Option option25 = new Option(question8, "Ruoka on herkullista");
			Option option26 = new Option(question8, "Kavereiden kanssa hengaus");
			Option option27 = new Option(question8, "Ravintolan työntekijät ovat mukavia :)");
			Option option28 = new Option(question8, "Monipuolinen ruokatarjonta");
			Option option29 = new Option(question8, "Pitkät koulupäivät");
			Option option30 = new Option(question8, "En tiedä");
			
			Answer answer1 = new Answer(question6, "Enemmän kalaruokia");
			Answer answer2 = new Answer(question6, "kasvisruokaa");
			Answer answer3 = new Answer(question6, "hampurilaisia, kebabia, hot dogeja ja tacoja");
			
			Answer answer4 = new Answer(animalQ1, "no");
			Answer answer5 = new Answer(animalQ2, "Woof");
			Answer answer6 = new Answer(animalQ3, "Uh, Meow?");
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
			//qrepository.save(question66);
			qrepository.save(question7);
			qrepository.save(question8);
			qrepository.save(question9);
			qrepository.save(animalQ1);
			qrepository.save(animalQ2);
			qrepository.save(animalQ3);
			
			arepository.save(answer1);
			arepository.save(answer2);
			arepository.save(answer3);
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
			orepository.save(option7);
			orepository.save(option8);
			orepository.save(option9);
			orepository.save(option10);
			orepository.save(option11);
			orepository.save(option12);
			orepository.save(option13);
			orepository.save(option14);
			orepository.save(option15);
			orepository.save(option16);
			orepository.save(option17);
			orepository.save(option18);
			orepository.save(option19);
			orepository.save(option20);
			orepository.save(option21);
			orepository.save(option22);
			orepository.save(option23);
			orepository.save(option24);
			orepository.save(option25);
			orepository.save(option26);
			orepository.save(option27);
			orepository.save(option28);
			orepository.save(option29);
			orepository.save(option30);
			
			//Radiokysymys 1 vastaukset
			Answer answer11 = new Answer(question1, option1.getOption());
			Answer answer12 = new Answer(question1, option2.getOption());
			Answer answer13 = new Answer(question1, option3.getOption());
			Answer answer14 = new Answer(question1, option4.getOption());
			Answer answer15 = new Answer(question1, option3.getOption());
			Answer answer16 = new Answer(question1, option2.getOption());
			Answer answer17 = new Answer(question1, option3.getOption());
			Answer answer18 = new Answer(question1, option1.getOption());
			//Radiokysymys 2 vastaukset
			Answer answer19 = new Answer(question2, option8.getOption());
			Answer answer20 = new Answer(question2, option8.getOption());
			Answer answer21 = new Answer(question2, option7.getOption());
			Answer answer22 = new Answer(question2, option8.getOption());
			Answer answer23 = new Answer(question2, option7.getOption());
			Answer answer24 = new Answer(question2, option6.getOption());
			Answer answer25 = new Answer(question2, option7.getOption());
			Answer answer26 = new Answer(question2, option5.getOption());
			//Radiokysymys 3 vastaukset
			Answer answer27 = new Answer(question3, option9.getOption());
			Answer answer28 = new Answer(question3, option12.getOption());
			Answer answer29 = new Answer(question3, option11.getOption());
			Answer answer30 = new Answer(question3, option12.getOption());
			Answer answer31 = new Answer(question3, option9.getOption());
			Answer answer32 = new Answer(question3, option10.getOption());
			Answer answer33 = new Answer(question3, option11.getOption());
			Answer answer34 = new Answer(question3, option12.getOption());
			//Radiokysymys 4 vastaukset
			Answer answer35 = new Answer(question4, option13.getOption());
			Answer answer36 = new Answer(question4, option14.getOption());
			Answer answer37 = new Answer(question4, option14.getOption());
			Answer answer38 = new Answer(question4, option15.getOption());
			Answer answer39 = new Answer(question4, option14.getOption());
			Answer answer40 = new Answer(question4, option14.getOption());
			Answer answer41 = new Answer(question4, option13.getOption());
			Answer answer42 = new Answer(question4, option13.getOption());
			//Radiokysymys 5 vastaukset
			Answer answer43 = new Answer(question5, option16.getOption());
			Answer answer44 = new Answer(question5, option16.getOption());
			Answer answer45 = new Answer(question5, option16.getOption());
			Answer answer46 = new Answer(question5, option17.getOption());
			Answer answer47 = new Answer(question5, option18.getOption());
			Answer answer48 = new Answer(question5, option17.getOption());
			Answer answer49 = new Answer(question5, option17.getOption());
			Answer answer50 = new Answer(question5, option16.getOption());
			//Radiokysymys 7 vastaukset
			Answer answer51 = new Answer(question7, option19.getOption());
			Answer answer52 = new Answer(question7, option19.getOption());
			Answer answer53 = new Answer(question7, option20.getOption());
			Answer answer54 = new Answer(question7, option20.getOption());
			Answer answer55 = new Answer(question7, option21.getOption());
			Answer answer56 = new Answer(question7, option22.getOption());
			Answer answer57 = new Answer(question7, option20.getOption());
			Answer answer58 = new Answer(question7, option23.getOption());
			//Multiselectkysymys 8 vastaukset
			Answer answer59 = new Answer(question8, option24.getOption());
			Answer answer60 = new Answer(question8, option24.getOption());
			Answer answer61 = new Answer(question8, option25.getOption());
			Answer answer62 = new Answer(question8, option25.getOption());
			Answer answer63 = new Answer(question8, option25.getOption());
			Answer answer64 = new Answer(question8, option26.getOption());
			Answer answer65 = new Answer(question8, option26.getOption());
			Answer answer66 = new Answer(question8, option27.getOption());
			Answer answer67 = new Answer(question8, option28.getOption());
			Answer answer68 = new Answer(question8, option28.getOption());
			Answer answer69 = new Answer(question8, option29.getOption());
			Answer answer70 = new Answer(question8, option27.getOption());
			Answer answer71 = new Answer(question8, option30.getOption());
			Answer answer72 = new Answer(question9, "Ei moitittavaa :)");
			Answer answer73 = new Answer(question9, "Ei moitittavaa :)");
			Answer answer74 = new Answer(question9, "Vesihana voisi olla nopeampi");
			
			// Tallennetaan radiokysymysten vastaukset
			
			arepository.save(answer11);
			arepository.save(answer12);
			arepository.save(answer13);
			arepository.save(answer14);
			arepository.save(answer15);
			arepository.save(answer16);
			arepository.save(answer17);
			arepository.save(answer18);
			arepository.save(answer19);
			arepository.save(answer20);
			arepository.save(answer21);
			arepository.save(answer22);
			arepository.save(answer23);
			arepository.save(answer24);
			arepository.save(answer25);
			arepository.save(answer26);
			arepository.save(answer27);
			arepository.save(answer28);
			arepository.save(answer29);
			arepository.save(answer30);
			arepository.save(answer31);
			arepository.save(answer32);
			arepository.save(answer33);
			arepository.save(answer34);
			arepository.save(answer35);
			arepository.save(answer36);
			arepository.save(answer37);
			arepository.save(answer38);
			arepository.save(answer39);
			arepository.save(answer40);
			arepository.save(answer41);
			arepository.save(answer42);
			arepository.save(answer43);
			arepository.save(answer44);
			arepository.save(answer45);
			arepository.save(answer46);
			arepository.save(answer47);
			arepository.save(answer48);
			arepository.save(answer49);
			arepository.save(answer50);
			arepository.save(answer51);
			arepository.save(answer52);
			arepository.save(answer53);
			arepository.save(answer54);
			arepository.save(answer55);
			arepository.save(answer56);
			arepository.save(answer57);
			arepository.save(answer58);
			arepository.save(answer59);
			arepository.save(answer60);
			arepository.save(answer61);
			arepository.save(answer62);
			arepository.save(answer63);
			arepository.save(answer64);
			arepository.save(answer65);
			arepository.save(answer66);
			arepository.save(answer67);
			arepository.save(answer68);
			arepository.save(answer69);
			arepository.save(answer70);
			arepository.save(answer71);
			arepository.save(answer72);
			arepository.save(answer73);
			arepository.save(answer74);
			
			
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