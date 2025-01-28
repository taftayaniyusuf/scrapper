package com.taftayani.scrapper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Solution {
	public static void main(String[] args) {

		//test
		
		final String url = "https://www.cermati.com/karir";
		List<Departement> deps = new ArrayList<>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements els = doc.getElementsByClass("panel-heading");
			els.parallelStream().forEach(i -> deps.add(parseDepartement(i)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		outputStreamWriter(deps);

	}

	public static Departement parseDepartement(Element el) {
		Element elDep = el.nextElementSibling();
		String departmentName = el.select("div.list-title").text();
		System.out.println("parse departement:" + departmentName + "...");
		Elements listJobEl = elDep.select("a");
		List<Job> jobs = new ArrayList<>();
		listJobEl.parallelStream().forEach(i -> jobs.add(parseJobDetail(i)));
		Departement dep = new Departement(departmentName, jobs);
		return dep;
	}

	public static Job parseJobDetail(Element jobEl) {
		String url = jobEl.attr("href");
		Job job = new Job();
		try {

			Document doc = Jsoup.connect(url).get();
			String title = doc.select("h1.job-title").text();
			System.out.println("parse job:" + title + "...");
			String location = doc.select("span.job-detail").text();
			String postedBy = doc.select("h3.details-title").text();

			List<String> description = new ArrayList<>();
			Elements descEl = doc.select("#st-jobDescription li");
			descEl.parallelStream().forEach(i -> description.add(i.text()));

			List<String> qualification = new ArrayList<>();
			Elements quaEl = doc.select("#st-qualifications li");
			quaEl.parallelStream().forEach(i -> qualification.add(i.text()));
			job = new Job(title, location, description, qualification, postedBy);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return job;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void outputStreamWriter(List<Departement> departements) {
		System.out.println("Write /solutin.json ...");
		Map<String, Object> map = new HashMap();
		departements.parallelStream().forEach(i -> map.put(i.getDepartementName(), i.getJobs()));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (OutputStream out = new FileOutputStream("./solution.json");
				Writer writer = new OutputStreamWriter(out, "UTF-8")) {
			writer.write(gson.toJson(map));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Success...");
	}

}
