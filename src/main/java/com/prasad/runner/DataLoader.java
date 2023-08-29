package com.prasad.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.prasad.entity.Courses;
import com.prasad.entity.EnquiryStatus;
import com.prasad.repositary.CoursesRepo;
import com.prasad.repositary.EnquiryStatusRepo;

@Component
public class DataLoader implements ApplicationRunner {
	@Autowired
	private CoursesRepo repo;

	@Autowired
	private EnquiryStatusRepo enqRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		repo.deleteAll();
		
		Courses c = new Courses();
		c.setCourseName("Java Fullstack");

		Courses c1 = new Courses();
		c1.setCourseName("DeveOps");

		Courses c2 = new Courses();
		c2.setCourseName("AWS");
		List<Courses> list = Arrays.asList(c, c1, c2);
		repo.saveAll(list);

		
		
		enqRepo.deleteAll();

		EnquiryStatus es = new EnquiryStatus();
		es.setStatusname("New");

		EnquiryStatus es1 = new EnquiryStatus();
		es1.setStatusname("Enrolled");

		EnquiryStatus es2 = new EnquiryStatus();
		es2.setStatusname("Lost");

		List<EnquiryStatus> asList = Arrays.asList(es, es1, es2);

		enqRepo.saveAll(asList);

	}

}
