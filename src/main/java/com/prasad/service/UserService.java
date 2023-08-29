package com.prasad.service;

import org.springframework.stereotype.Service;

import com.prasad.binding.LoginForm;
import com.prasad.binding.SignUpForm;
import com.prasad.binding.UnlockForm;

@Service
public interface UserService {
	public boolean  registration(SignUpForm signupForm);
    
	public boolean unlockAccount(UnlockForm unlockForm);
	
	public String login(LoginForm loginForm);

	public boolean forgotPwd(String useremail);



}
