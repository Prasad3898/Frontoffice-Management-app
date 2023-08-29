package com.prasad.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prasad.binding.LoginForm;
import com.prasad.binding.SignUpForm;
import com.prasad.binding.UnlockForm;
import com.prasad.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService service;
	
	
	
	@PostMapping("/signup")
	public String handleSignUp(@ModelAttribute("user") SignUpForm signup, Model model) {
		boolean status = service.registration(signup);
		if (status) {
			model.addAttribute("sucmsg", "Registered Success");
		} else {
			model.addAttribute("errormsg", "Problem  Occured");
		}
		return "signup";
	}

	@GetMapping("/signup")
	public String signUp(Model model) {
		model.addAttribute("user", new SignUpForm());

		return "signup";
	}

	@GetMapping("/unlock")
	public String loadUnlockForm(@RequestParam String useremail, Model model) {

		UnlockForm unlockForm = new UnlockForm();
		unlockForm.setUseremail(useremail);

		model.addAttribute("unlock", unlockForm);

		return "unlock";
	}

	@PostMapping("/unlock")
	public String unlockAccount(@ModelAttribute("unlock") UnlockForm unlock, Model model) {

		System.out.println(unlock);
		if (unlock.getNewPwd().equals(unlock.getConfirmPwd())) {
			boolean account = service.unlockAccount(unlock);

			if (account) {
				model.addAttribute("sucMsg", "Your Account Unlocked Successfully");
			} else {
				model.addAttribute("errMsg", "Given Temporary Pwd is Incorrect,check your email");
			}
		} else {
			model.addAttribute("errMsg", "New Pwd and Confirm Pwd should be same");
		}
		return "unlock";
	}

	@GetMapping("/login")
	public String loadLoginForm(Model model) {

		model.addAttribute("login", new LoginForm());
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("login") LoginForm login, Model model) {

		String status = service.login(login);

		if (status.contains("success")) {

			return "redirect:/dashboard";
		}

		model.addAttribute("errMsg", status);

		return "login";
	}

	@GetMapping("/forgot")
	public String loadForgotForm(Model model) {
		return "forgotPwd";
	}
	
	@PostMapping("/forgotPwd")
	public String forgot(@RequestParam("useremail") String useremail,Model model) {
		
		System.out.println(useremail);
		
		boolean pwd = service.forgotPwd(useremail);
		
		if(pwd) {
			model.addAttribute("succMsg", "Check your email");
		}
		else {
			model.addAttribute("errMsg", "Invalid Email");
		}
		
		return "forgotPwd";
		
	}

}
