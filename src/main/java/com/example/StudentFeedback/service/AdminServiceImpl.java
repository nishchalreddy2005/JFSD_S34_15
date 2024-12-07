package com.example.StudentFeedback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentFeedback.entity.Admin;
import com.example.StudentFeedback.entity.Course;
import com.example.StudentFeedback.entity.CourseTeacher;
import com.example.StudentFeedback.entity.Feedback;
import com.example.StudentFeedback.entity.Student;
import com.example.StudentFeedback.entity.Teacher;
import com.example.StudentFeedback.repository.AdminRepository;
import com.example.StudentFeedback.repository.CourseRepository;
import com.example.StudentFeedback.repository.CourseTeacherRepository;
import com.example.StudentFeedback.repository.FeedbackRepository;
import com.example.StudentFeedback.repository.StudentRepository;
import com.example.StudentFeedback.repository.TeacherRepository;

@Service
public class AdminServiceImpl implements AdminService
{
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private CourseTeacherRepository courseTeacherRepository;
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Admin checkAdminLogin(String username, String password) {
		return adminRepository.checkAdminLogin(username, password);
	}

	@Override
	public Course checkIdUnique(String code) {
		return courseRepository.checkIdUnique(code);
	}

	@Override
	public String addCourse(Course course) {
		courseRepository.save(course);
		return "Course Added Successfully";
	}

	@Override
	public List<Course> viewAll() {
		return courseRepository.findAll();
	}

	@Override
	public List<Teacher> viewTeacher() {
		return teacherRepository.findAll();
	}

	@Override
	public Course findCourseByCode(String courseCode) {
		return courseRepository.checkIdUnique(courseCode);
	}

	@Override
	public Teacher findTeacherById(int id) {
		return teacherRepository.findTeacherById(id);
	}

	@Override
	public void saveAssociation(CourseTeacher association) 
	{
		courseTeacherRepository.save(association);
	}

	@Override
	public List<CourseTeacher> getCourses() {
		return courseTeacherRepository.findAll();
	}

	@Override
	public List<Feedback> getFeedback(int tid, String ccode) {
		return feedbackRepository.findByTeacherIdAndCourseCode(tid, ccode);
	}

	@Override
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	@Override
	public void deleteStudent(String id) {
		studentRepository.deleteById(id);
	}
	
	

}
