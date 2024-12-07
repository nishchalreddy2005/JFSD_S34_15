package com.example.StudentFeedback.service;

import java.util.List;

import com.example.StudentFeedback.entity.Course;
import com.example.StudentFeedback.entity.CourseTeacher;
import com.example.StudentFeedback.entity.CourseTeacherFeedback;
import com.example.StudentFeedback.entity.Feedback;
import com.example.StudentFeedback.entity.Teacher;

public interface TeacherService 
{
	public Teacher checkTeacherLogin(int tuid, String tpwd);

	public List<CourseTeacher> getCourses(int id);
	
	public Course getCourse(String ccode);
	
	public void add(CourseTeacherFeedback courseTeacherFeedback);

	public List<Feedback> getFeedback(int tid, String ccode);
}
