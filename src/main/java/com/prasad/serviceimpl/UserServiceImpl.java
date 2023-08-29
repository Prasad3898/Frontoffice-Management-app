package com.prasad.serviceimpl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasad.binding.LoginForm;
import com.prasad.binding.SignUpForm;
import com.prasad.binding.UnlockForm;
import com.prasad.entity.User;
import com.prasad.repositary.UserRepo;
import com.prasad.service.UserService;
import com.prasad.utility.EmailUtils;
import com.prasad.utility.PwdUtils;

@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserRepo repo;
	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
	private HttpSession session;

	@Override
	public boolean registration(SignUpForm signup) {

		User useremail = repo.findByuseremail(signup.getUseremail());

		if (useremail != null) {
			return false;
		}

		// copy data from binding object to entity object
		User entity = new User();
		BeanUtils.copyProperties(signup, entity);

		// generate random password to set to object
		// calling to Util password
		String tempPwd = PwdUtils.randomPwd();
		// set account status as locked

		entity.setAccountStatus("Locked");
		entity.setPwd(tempPwd);
		// insert record
		repo.save(entity);
		// send email to unlock the account
		String to = signup.getUseremail();
		String subject = "Unlock your account | ASHOKIT";
		StringBuffer body = new StringBuffer(" ");

		body.append("<h1>To use link unlock your account</h1> ");
		body.append("Tempory pwd:" + tempPwd);
		body.append("<br/>");
		body.append(
				"<a href=\"http://localhost:9091/unlock?useremail=" + to + "\">Click Here To Unlock Your Account</a>");
		emailUtils.sendMail(to, subject, body.toString());
		return true;
	}

	@Override
	public boolean unlockAccount(UnlockForm unlock) {

		User entity = repo.findByuseremail(unlock.getUseremail());

		if (entity.getPwd().equals(unlock.getTempPwd())) {
			entity.setPwd(unlock.getNewPwd());
			entity.setAccountStatus("UnLocked");
			repo.save(entity);
			return true;
		} else {

			return false;
		}
	}

	@Override
	public String login(LoginForm loginForm) {
		User entity = repo.findByuseremailAndPwd(loginForm.getUseremail(), loginForm.getPwd());

		if (entity == null) {
			return "Invalid credentials";
		}
		if (entity.getAccountStatus().equals("LOCKED")) {
			return "Your Account is Locked";
		}
		
		session.setAttribute("userId", entity.getUserid());
		
		return "success";
	}

	@Override
	public boolean forgotPwd(String useremail) {
		
		User status = repo.findByuseremail(useremail);
		
		if(status == null) {
			
			return false;
			
		}
		else {
		String subject = "Recover Password";
		String body = "Your Pwd:" + status.getPwd();
		
		emailUtils.sendMail(useremail, subject, body);
		
		return true;
	}

}
}
