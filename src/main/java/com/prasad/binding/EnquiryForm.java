package com.prasad.binding;

import lombok.Data;
/*
 * Binding class used to capture the form data and store data 
 * Binding class also communicate with Entity class
 */
@Data
public class EnquiryForm {
	private String studentName;
	private Long  studentPhno;
	private String classMode;
	private String courseName;
	private String enquiryStatus;
	

}
