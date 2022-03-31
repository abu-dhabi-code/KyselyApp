package com.abu.dhabi.KyselyApp.domain;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Survey {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	// private String surveyName;
	
	public Survey() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	
	
	
	
	
	
}
