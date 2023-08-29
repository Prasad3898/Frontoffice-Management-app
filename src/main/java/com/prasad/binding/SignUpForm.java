package com.prasad.binding;

import lombok.Data;
/*
 * Binding class used to capture the form data and store data 
 * Binding class also communicate with Entity class
 */
@Data
public class SignUpForm {

	private String username;
	private String useremail;
	private long phno;
	public String getUseremail() {
		// TODO Auto-generated method stub
		return null;
	}

}
