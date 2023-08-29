package com.prasad.binding;

import lombok.Data;

/*
 * Binding class used to capture the form data and store data 
 * Binding class also communicate with Entity class
 */
@Data

public class DashboardResponse {
	
	private Integer totalEnquiries;

	private Integer enrolled;
	
	private Integer lost;

}
