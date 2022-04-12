package com.abu.dhabi.KyselyApp.domain;

import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long>{

	// findBySurveyName
}
