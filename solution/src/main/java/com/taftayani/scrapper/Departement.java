package com.taftayani.scrapper;

import java.util.List;


public class Departement {
	private String departementName;
	private List<Job> jobs;
	
	
	public Departement(String departementName, List<Job> jobs) {
		super();
		this.departementName = departementName;
		this.jobs = jobs;
	}
	
	public String getDepartementName() {
		return departementName;
	}
	public void setDepartementName(String departementName) {
		this.departementName = departementName;
	}
	public List<Job> getJobs() {
		return jobs;
	}
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	} 
}
