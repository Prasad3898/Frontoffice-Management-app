package com.prasad.binding;

import lombok.Data;
/*
 * Binding class used to capture the form data and store data 
 * Binding class also communicate with Entity class
 */
@Data
public class UnlockForm {
	//password is only one we replace temporary pwd to new password
	
	
	private String useremail;
	private String tempPwd;
	private String newPwd;
	private String confirmPwd;
	public void setUseremail(String useremail2) {
		// TODO Auto-generated method stub
		
	}
	public Object getNewPwd() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getConfirmPwd() {
		// TODO Auto-generated method stub
		return null;
	}
}
