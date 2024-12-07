package com.example.StudentFeedback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentFeedback.entity.Course;
import com.example.StudentFeedback.entity.CourseTeacher;
import com.example.StudentFeedback.entity.CourseTeacherFeedback;
import com.example.StudentFeedback.entity.CourseTeacherStudent;
import com.example.StudentFeedback.entity.Feedback;
import com.example.StudentFeedback.entity.Student;
import com.example.StudentFeedback.entity.Teacher;
import com.example.StudentFeedback.repository.CourseRepository;
import com.example.StudentFeedback.repository.CourseTeacherFeedbackRepository;
import com.example.StudentFeedback.repository.CourseTeacherRepository;
import com.example.StudentFeedback.repository.CourseTeacherStudentRepository;
import com.example.StudentFeedback.repository.FeedbackRepository;
import com.example.StudentFeedback.repository.StudentRepository;
import com.example.StudentFeedback.repository.TeacherRepository;

@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseTeacherRepository courseTeacherRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private CourseTeacherStudentRepository courseTeacherStudentRepository;

	@Autowired
	private CourseTeacherFeedbackRepository courseTeacherFeedbackRepository;
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Override
	public String studentRegistration(Student student) 
	{
		studentRepository.save(student);
		return "Registration Successfull";	
	}

	@Override
	public Student checkIdUnique(String id) {
		return studentRepository.checkIdUnique(id);
	}

	@Override
	public Student checkStudentLogin(String email, String password) {
		return studentRepository.checkStudentLogin(email, password);
	}

	@Override
	public List<CourseTeacher> viewAll() {
		return courseTeacherRepository.findAll();
	}

	@Override
	public Course findCourse(String code) {
		return courseRepository.checkIdUnique(code);
	}

	@Override
	public Teacher findTeacher(int id) {
		return teacherRepository.findTeacherById(id);
	}

	@Override
	public void saveAssociation(CourseTeacherStudent association) 
	{
		courseTeacherStudentRepository.save(association);
	}

	@Override
	public List<CourseTeacherStudent> getCourses(String sid) {
		return courseTeacherStudentRepository.findByStudentId(sid);
	}

	@Override
	public CourseTeacherFeedback getQuestions(int tid, String ccode) {
		return courseTeacherFeedbackRepository.findByTeacherIdAndCourseCode(tid, ccode);
	}

	@Override
	public void saveFeedback(Feedback feedback) {
		feedbackRepository.save(feedback);
	}

	@Override
	public void updatePassword(String token, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String generateResetToken(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
