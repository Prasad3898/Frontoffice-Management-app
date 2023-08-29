package com.prasad.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "StudentEnquiryRepo")
public class EnquiryStatus {

	@Id
	@GeneratedValue
	private Integer statusid;
	private String statusname;
	public void setStatusname(String string) {
		// TODO Auto-generated method stub
		
	}

}
