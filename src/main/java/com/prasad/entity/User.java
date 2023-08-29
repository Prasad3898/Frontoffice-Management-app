package com.prasad.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "User_tbl")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer userid;
	private String username;
	private String useremail;
	private long phno;
	private String pwd;
	private String accountStatus;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<StudentEnquiries> enquiries;

	public void setAccountStatus(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setPwd(String tempPwd) {
		// TODO Auto-generated method stub
		
	}

}
