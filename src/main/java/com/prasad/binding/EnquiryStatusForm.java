package com.prasad.binding;

import lombok.Data;

/*
 * Binding class used to capture the form data and store data 
 * Binding class also communicate with Entity class
 */
@Data
public class EnquiryStatusForm {
	
	private String classMode;
	private String courseName;
	private String enquiryStatus;
	public void setCourseName(String cname) {
		// TODO Auto-generated method stub
		
	}
	public void setEnquiryStatus(String status) {
		// TODO Auto-generated method stub
		
	}
	public void setClassMode(String mode) {
		// TODO Auto-generated method stub
		
	}

}
