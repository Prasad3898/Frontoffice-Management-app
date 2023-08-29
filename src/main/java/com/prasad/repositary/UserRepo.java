package com.prasad.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prasad.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
   
	public User findByuseremail(String useremail);
	
	public User findByuseremailAndPwd(String useremail,String pwd);


}
