package com.example.StudentFeedback.service;

import java.util.List;

import com.example.StudentFeedback.entity.Course;
import com.example.StudentFeedback.entity.CourseTeacher;
import com.example.StudentFeedback.entity.CourseTeacherFeedback;
import com.example.StudentFeedback.entity.CourseTeacherStudent;
import com.example.StudentFeedback.entity.Feedback;
import com.example.StudentFeedback.entity.Student;
import com.example.StudentFeedback.entity.Teacher;
import com.example.StudentFeedback.repository.CourseTeacherRepository;

public interface StudentService 
{
	public String studentRegistration(Student student);
	
	public Student checkIdUnique(String id);
	
	public Student checkStudentLogin(String email,String password);

	public List<CourseTeacher> viewAll();
	
	public Course findCourse(String code);
	
	public Teacher findTeacher(int id);

	public void saveAssociation(CourseTeacherStudent association);
	
	public List<CourseTeacherStudent> getCourses(String sid);

	public CourseTeacherFeedback getQuestions(int tid, String ccode);
	
	public void saveFeedback(Feedback feedback);

	public void updatePassword(String token, String newPassword);

	public String generateResetToken(String email);
}
