package com.example.StudentFeedback.service;

import java.util.List;

import com.example.StudentFeedback.entity.Admin;
import com.example.StudentFeedback.entity.Course;
import com.example.StudentFeedback.entity.CourseTeacher;
import com.example.StudentFeedback.entity.Feedback;
import com.example.StudentFeedback.entity.Student;
import com.example.StudentFeedback.entity.Teacher;

public interface AdminService 
{

	public Admin checkAdminLogin(String auname, String apwd);

	public Course checkIdUnique(String code);

	public String addCourse(Course course);
	
	public List<Course> viewAll();
	
	public List<Teacher> viewTeacher();

	public Course findCourseByCode(String courseCode);
	
	public Teacher findTeacherById(int id);

	public void saveAssociation(CourseTeacher association);

	public List<CourseTeacher> getCourses();

	public List<Feedback> getFeedback(int tid, String ccode);

	public List<Student> getStudents();

	public void deleteStudent(String id);

}
