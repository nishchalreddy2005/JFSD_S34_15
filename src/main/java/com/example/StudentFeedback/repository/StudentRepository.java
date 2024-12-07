package com.example.StudentFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.StudentFeedback.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>
{
	@Query("select s from Student s where s.email=?1 and s.password=?2")
	public Student checkStudentLogin(String email,String password);
	
	@Query("select s from Student s where s.id=?1")
	public Student checkIdUnique(String id);
}
