package com.prasad.service;

import java.util.List;

import com.prasad.binding.DashboardResponse;
import com.prasad.binding.EnquiryForm;
import com.prasad.binding.EnquiryStatusForm;
import com.prasad.entity.StudentEnquiries;

public interface EnquiryService {



	public DashboardResponse responsePage(Integer userId);

	public List<String>getCoursesname();
	
	public List<String>getEnqStatus();
	
	public boolean saveEnquiry(EnquiryForm enqForm);
	
	
	public List<StudentEnquiries> getEnquiries();

	public List<StudentEnquiries> getFilteredEnqs(EnquiryStatusForm criteria, Integer userId);
	

}
