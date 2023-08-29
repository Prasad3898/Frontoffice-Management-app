package com.prasad.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prasad.entity.StudentEnquiries;


public interface StudentEnquiryRepo extends JpaRepository<StudentEnquiries, Integer>{

}
