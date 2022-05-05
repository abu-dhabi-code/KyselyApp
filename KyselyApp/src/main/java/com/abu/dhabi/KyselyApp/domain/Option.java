package com.abu.dhabi.KyselyApp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class Option {
	
	@Id
	@NonNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String option;
	
	
	public Option() {
		super();
	}
	
	public Option(String option) {
		super();
		this.option = option;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getOption() {
		return option;
	}


	public void setOption(String option) {
		this.option = option;
	}
	
}
