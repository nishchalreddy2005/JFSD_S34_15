package com.example.StudentFeedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.StudentFeedback.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer>
{
	@Query("select t from Teacher t where t.id=?1 and t.password=?2")
	public Teacher checkTeacherLogin(int id,String password);
	
	@Query("select t from Teacher t where t.id=?1")
	public Teacher findTeacherById(int id);
	
}
