package com.prasad.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Courses")
public class Courses {
	@Id
	@GeneratedValue
	private Integer courseId;
	private String courseName;
	public void setCourseName(String string) {
		// TODO Auto-generated method stub
		
	}

}
