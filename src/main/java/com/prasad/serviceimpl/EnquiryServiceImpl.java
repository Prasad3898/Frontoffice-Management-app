package com.prasad.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasad.binding.DashboardResponse;
import com.prasad.binding.EnquiryForm;
import com.prasad.binding.EnquiryStatusForm;
import com.prasad.entity.Courses;
import com.prasad.entity.EnquiryStatus;
import com.prasad.entity.StudentEnquiries;
import com.prasad.entity.User;
import com.prasad.repositary.CoursesRepo;
import com.prasad.repositary.EnquiryStatusRepo;
import com.prasad.repositary.StudentEnquiryRepo;
import com.prasad.repositary.UserRepo;
import com.prasad.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private UserRepo repo;

    @Autowired
    private CoursesRepo courseRepo;
    
    @Autowired
    private EnquiryStatusRepo enqStatusRepo;
    
    @Autowired
    private StudentEnquiryRepo enqRepo;
    
    @Autowired
    private HttpSession session;
    
    

	@Override
	public DashboardResponse responsePage(Integer userId) {

		DashboardResponse response = new DashboardResponse();

		Optional<User> findById = repo.findById(userId);

		if (findById.isPresent()) {

			User user = findById.get();

			List<StudentEnquiries> enquiries = user.getEnquiries();

			Integer totalcnt = enquiries.size();

			Integer enrolledcnt = enquiries.stream().filter(e -> e.getEnquiryStatus().equals("Enrolled"))
					.collect(Collectors.toList()).size();

			Integer lostcnt = enquiries.stream().filter(e -> e.getEnquiryStatus().equals("Lost"))
					.collect(Collectors.toList()).size();

			response.setTotalEnquiries(totalcnt);
			response.setEnrolled(enrolledcnt);
			response.setLost(lostcnt);

		}

		return response;
	}

	@Override
	public List<String> getCoursesname() {
		

     List<Courses> findAll = courseRepo.findAll();
		
		List<String> names = new ArrayList<>();
		
		for(Courses entity : findAll) {
			names.add(entity.getCourseName());
		}
		return names;

	}

	@Override
	public List<String> getEnqStatus() {
		
		List<EnquiryStatus> findAll = enqStatusRepo.findAll();
		
		List<String> status = new ArrayList<>();
		
		for(EnquiryStatus entity : findAll) {
			
			status.add(entity.getStatusname());
			
		}
		
		return status;
	}
	
	@Override
	public boolean saveEnquiry(EnquiryForm enqForm) {
		
		StudentEnquiries enqEntity = new StudentEnquiries();
		BeanUtils.copyProperties(enqForm, enqEntity);
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		User user = repo.findById(userId).get();
		enqEntity.setUser(user);
		
		
		
		enqRepo.save(enqEntity);
		
		return true;
	}
	
	
	@Override
	public List<StudentEnquiries> getEnquiries() {
		
		Integer userId = (Integer) session.getAttribute("userId");
		Optional<User> findById = repo.findById(userId);
		
		if(findById.isPresent()) {
			User user = findById.get();
			
			List<StudentEnquiries> enquiries = user.getEnquiries();
			return enquiries;
		}
		
		
		return null;
	}
	
	@Override
	public List<StudentEnquiries> getFilteredEnqs(EnquiryStatusForm criteria, Integer userId) {
		Integer userId1 = (Integer) session.getAttribute("userId");
		Optional<User> findById = repo.findById(userId1);
		
		if(findById.isPresent()) {
			User user = findById.get();
			
			List<StudentEnquiries> enquiries = user.getEnquiries();
			if(null!= criteria.getCourseName()& !"" .equals(criteria.getCourseName()) ) {
				enquiries = enquiries.stream()
						.filter(e-> e.getCourseName().equals(criteria.getCourseName()))
								.collect(Collectors.toList());
			}
			
			if(null!= criteria.getEnquiryStatus()& !"" .equals(criteria.getEnquiryStatus()) ) {
				enquiries = enquiries.stream()
						.filter(e-> e.getEnquiryStatus().equals(criteria.getEnquiryStatus()))
								.collect(Collectors.toList());
			}
			if(null!= criteria.getClassMode()& !"" .equals(criteria.getClassMode()) ) {
				enquiries = enquiries.stream()
						.filter(e-> e.getClassMode().equals(criteria.getClassMode()))
								.collect(Collectors.toList());
			}
			
			return enquiries;
			
			
			
		}
		
		
		return null;
	}

}
