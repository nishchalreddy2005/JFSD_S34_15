package com.example.StudentFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.StudentFeedback.entity.Course;

public interface CourseRepository extends JpaRepository<Course, String>
{
	@Query("select c from Course c where c.code=?1")
	public Course checkIdUnique(String code);
}
