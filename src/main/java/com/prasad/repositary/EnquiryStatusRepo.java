package com.prasad.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prasad.entity.EnquiryStatus;
@Repository
public interface EnquiryStatusRepo extends JpaRepository<EnquiryStatus, Integer> {

}
