package com.prasad.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prasad.binding.DashboardResponse;
import com.prasad.binding.EnquiryForm;
import com.prasad.binding.EnquiryStatusForm;
import com.prasad.entity.StudentEnquiries;
import com.prasad.repositary.StudentEnquiryRepo;
import com.prasad.service.EnquiryService;

@Controller
public class EnquiryController {

	@Autowired
	private EnquiryService enqService;
	
	@Autowired
	private StudentEnquiryRepo studentRepo;

	@Autowired
	private HttpSession session;

	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}

	@GetMapping("/dashboard")
	public String loadDashBoardForm(Model model) {

		Integer userId = (Integer) session.getAttribute("userId");

		DashboardResponse responsePage = enqService.responsePage(userId);

		model.addAttribute("responsePage", responsePage);

		return "dashboard";
	}

	@GetMapping("/add-enquiry")
	public String getAddEnquiry(Model model) {

		List<String> coursesname = enqService.getCoursesname();

		List<String> enqStatus = enqService.getEnqStatus();

		EnquiryForm enqForm = new EnquiryForm();

		model.addAttribute("CourseNames", coursesname);
		model.addAttribute("EnquiryStatus", enqStatus);
		model.addAttribute("EnqForm", enqForm);

		return "addEnq";
	}

	@PostMapping("/add-enquiry")
	public String loadAddEnquiry(@ModelAttribute("EnqForm") EnquiryForm enqForm, Model model) {

		System.out.println(enqForm);

		boolean enquiry = enqService.saveEnquiry(enqForm);

		if (enquiry) {
			model.addAttribute("succMsg", "Enquiry Data Added Successfully");
		} else {
			model.addAttribute("errMsg", "Problem Occured");
		}

		return "addEnq";
	}

	private void initForm(Model model) {
		List<String> coursesname = enqService.getCoursesname();

		List<String> enqStatus = enqService.getEnqStatus();

		EnquiryForm enqForm = new EnquiryForm();

		model.addAttribute("CourseNames", coursesname);
		model.addAttribute("EnquiryStatus", enqStatus);
		model.addAttribute("EnqForm", enqForm);

	}

	@GetMapping("/view-enquiries")
	public String loadViewEnquiry(Model model) {
		initForm(model);
		model.addAttribute("searchForm", new StudentEnquiries());
		List<StudentEnquiries> enquiries = enqService.getEnquiries();
		model.addAttribute("enquiries", enquiries);

		return "viewEnq";
	}

	@GetMapping("/filter-enquries")
	public String getFilteredEnqs(@RequestParam("cname") String cname, @RequestParam("status") String status,
			@RequestParam("mode") String mode, Model model) {

		EnquiryStatusForm criteria = new EnquiryStatusForm();
		criteria.setCourseName(cname);
		criteria.setEnquiryStatus(status);
		criteria.setClassMode(mode);

		Integer userId = (Integer) session.getAttribute("userId");

		List<StudentEnquiries> filteredEnqs = enqService.getFilteredEnqs(criteria, userId);

		model.addAttribute("enquries", filteredEnqs);

		return "filterenquiriespage";
	}
	
	
	@GetMapping("/edit")
	public String edit(@RequestParam("enquiryId") Integer enquiryId, Model model) {

		Optional<StudentEnquiries> findById = studentRepo.findById(enquiryId);
		if (findById.isPresent()) {
			StudentEnquiries studentEnqEntity = findById.get();
			model.addAttribute("EnqForm", studentEnqEntity);

		}
		return "addEnq";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("enquiryId") Integer enquiryId, Model model) {

		studentRepo.deleteById(enquiryId);

		model.addAttribute("msg", "Product Deleted");
		model.addAttribute("enquires", studentRepo.findAll());
		return "viewEnq";

	}
	@GetMapping("/view")
	public String getAllproducts(Model model) {
		List<StudentEnquiries> list = studentRepo.findAll();
		model.addAttribute("enquires", list);
		return "viewEnq";
	}

}
