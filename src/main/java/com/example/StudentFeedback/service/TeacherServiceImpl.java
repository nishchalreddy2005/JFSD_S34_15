package com.example.StudentFeedback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentFeedback.entity.Course;
import com.example.StudentFeedback.entity.CourseTeacher;
import com.example.StudentFeedback.entity.CourseTeacherFeedback;
import com.example.StudentFeedback.entity.Feedback;
import com.example.StudentFeedback.entity.Teacher;
import com.example.StudentFeedback.repository.CourseRepository;
import com.example.StudentFeedback.repository.CourseTeacherFeedbackRepository;
import com.example.StudentFeedback.repository.CourseTeacherRepository;
import com.example.StudentFeedback.repository.FeedbackRepository;
import com.example.StudentFeedback.repository.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService
{

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private CourseTeacherRepository courseTeacherRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CourseTeacherFeedbackRepository courseTeacherFeedbackRepository;
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Override
	public Teacher checkTeacherLogin(int tuid, String tpwd) {
		return teacherRepository.checkTeacherLogin(tuid, tpwd);
	}

	@Override
	public List<CourseTeacher> getCourses(int id) {
		return courseTeacherRepository.findByTeacherId(id);
	}

	@Override
	public Course getCourse(String ccode) {
		return courseRepository.checkIdUnique(ccode);
	}

	@Override
	public void add(CourseTeacherFeedback courseTeacherFeedback) {
		courseTeacherFeedbackRepository.save(courseTeacherFeedback);
		
	}

	@Override
	public List<Feedback> getFeedback(int tid, String ccode) {
		return feedbackRepository.findByTeacherIdAndCourseCode(tid, ccode);
	}

	
}
