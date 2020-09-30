package com.taftayani.scrapper;

import java.util.List;

public class Job {
	
	
	public Job(String title, String location, List<String> description,
			List<String> qualification, String postedBy) {
		super();
		this.title = title;
		this.location = location;
		this.description = description;
		this.qualification = qualification;
		this.postedBy = postedBy;
	}
	public Job() {
		// TODO Auto-generated constructor stub
	}
	
	private String title;
	private String location;
	private List<String> description;
	private List<String> qualification;
	private String postedBy;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<String> getDescription() {
		return description;
	}
	public void setDescription(List<String> description) {
		this.description = description;
	}
	public List<String> getQualification() {
		return qualification;
	}
	public void setQualification(List<String> qualification) {
		this.qualification = qualification;
	}
	public String getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

}
