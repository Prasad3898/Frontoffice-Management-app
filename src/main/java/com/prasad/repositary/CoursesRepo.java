package com.prasad.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prasad.entity.Courses;

@Repository
public interface CoursesRepo extends JpaRepository<Courses, Integer> {

}
