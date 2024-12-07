package com.example.StudentFeedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.StudentFeedback.entity.Course;
import com.example.StudentFeedback.entity.CourseTeacher;
import com.example.StudentFeedback.entity.CourseTeacherFeedback;
import com.example.StudentFeedback.entity.CourseTeacherStudent;
import com.example.StudentFeedback.entity.Feedback;
import com.example.StudentFeedback.entity.Student;
import com.example.StudentFeedback.entity.Teacher;
import com.example.StudentFeedback.service.TeacherService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class TeacherController 
{
	@Autowired
    private TeacherService teacherService;
	
	@PostMapping("checkteacherlogin")
    public ModelAndView loginAdmin(HttpServletRequest request) {
    	
    	ModelAndView mv = new ModelAndView();
		
    	String tidString = request.getParameter("tid");
    	int tid = Integer.parseInt(tidString);
		String tpwd = request.getParameter("tpwd");
		
        Teacher teacher = teacherService.checkTeacherLogin(tid, tpwd);

        if (teacher != null) {
        	System.out.println("Login succe");
			HttpSession session = request.getSession();
			
			session.setAttribute("teacher", teacher);
			
        	mv.setViewName("teacherhome");
        } else {
            mv.addObject("message","Login Failed");
            mv.setViewName("teacheradminlogin");
        }
        return mv;
    }
	
	@GetMapping("teacherhome")
    public ModelAndView adminHome(HttpServletRequest request) {
    	HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

        if (session == null || session.getAttribute("teacher") == null) {
            // Session is null or does not contain a "admin" attribute, meaning the user is logged out
            ModelAndView mv = new ModelAndView();
            mv.setViewName("teacheradminlogin");
            mv.addObject("message", "Please log in to access this page.");
            return mv;
        }
    	ModelAndView mv = new ModelAndView();
		mv.setViewName("teacherhome");
		return mv;
    }
	
	@GetMapping("teacherlogout")
    public ModelAndView adminlogout(HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView();
    	
    	 // Invalidate the session to log out the Teacher
        HttpSession session = request.getSession(false); // get the existing session if it exists
        if (session != null) {
            session.invalidate(); // destroys the session
        }
    	
		mv.setViewName("home");
		return mv;
    }
	
	@GetMapping("tmycourses")
	public ModelAndView courses(HttpServletRequest request) {
	    HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

	    // Check if session is null or student is not logged in
	    if (session == null || session.getAttribute("teacher") == null) {
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("teacheradminlogin");  // Redirect to login page if not logged in
	        mv.addObject("message", "Please log in to access this page.");
	        return mv;
	    }

	    ModelAndView mv = new ModelAndView();
	    
	    // Retrieve the logged-in student
	    Teacher t = (Teacher) session.getAttribute("teacher");
	    
	    // Fetch the courses associated with the student using the student ID
	    List<CourseTeacher> courses = teacherService.getCourses(t.getId());
	    
	    // Add the list of courses to the ModelAndView object
	    mv.addObject("courses", courses);
	    
	    // Set the view to be rendered
	    mv.setViewName("tmycourses");  // JSP page name to render
	    
	    return mv;  // Return the ModelAndView
	}
	
	@PostMapping("addfeedback")
    public ModelAndView addFeedback(HttpServletRequest request) 
	{
		HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

	    // Check if session is null or student is not logged in
	    if (session == null || session.getAttribute("teacher") == null) {
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("teacheradminlogin");  // Redirect to login page if not logged in
	        mv.addObject("message", "Please log in to access this page.");
	        return mv;
	    }
	    
	    String ccode = request.getParameter("ccode");
	    
	    ModelAndView mv = new ModelAndView();
	    
	    // Retrieve the logged-in student
	    Teacher t = (Teacher) session.getAttribute("teacher");
	    
	    mv.addObject("ccode", ccode);
	    
	    mv.setViewName("addfeedback");  // JSP page name to render
	    
	    return mv;
    }
	
	@PostMapping("add")
	public ModelAndView add(HttpServletRequest request) {
		HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

	    // Check if session is null or student is not logged in
	    if (session == null || session.getAttribute("teacher") == null) {
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("teacheradminlogin");  // Redirect to login page if not logged in
	        mv.addObject("message", "Please log in to access this page.");
	        return mv;
	    }
	    
	    ModelAndView mv = new ModelAndView();
	    
	    // Retrieve the logged-in student
	    Teacher t = (Teacher) session.getAttribute("teacher");
	    
	    String q1 = request.getParameter("question1");
	    String q2 = request.getParameter("question2");
	    String q3 = request.getParameter("question3");
	    String q4 = request.getParameter("question4");
	    String q5 = request.getParameter("question5");
	    
	    String ccode = request.getParameter("ccode");
	    
	    System.out.println(q3);
	    System.out.println(q1);
	    
	    Course course = teacherService.getCourse(ccode);
	    
	    CourseTeacherFeedback courseTeacherFeedback = new CourseTeacherFeedback();
	    
	    courseTeacherFeedback.setQuestion1(q1);
	    courseTeacherFeedback.setQuestion2(q2);
	    courseTeacherFeedback.setQuestion3(q3);
	    courseTeacherFeedback.setQuestion4(q4);
	    courseTeacherFeedback.setQuestion5(q5);
	    courseTeacherFeedback.setCourse(course);
	    courseTeacherFeedback.setTeacher(t);
	    
	    teacherService.add(courseTeacherFeedback);
	    
	    mv.setViewName("tmycourses");
	    
	    return mv;
	    
	}
	
	@GetMapping("teacherprofile")
	public ModelAndView teacherprofile(HttpServletRequest request) 
	{
    	HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

        if (session == null || session.getAttribute("teacher") == null) {
            // Session is null or does not contain a "student" attribute, meaning the user is logged out
            ModelAndView mv = new ModelAndView();
            mv.setViewName("teacheradminlogin");
            mv.addObject("message", "Please log in to access this page.");
            return mv;
        }
        
		ModelAndView mv = new ModelAndView();
		mv.setViewName("teacherprofile");
		return mv;
	}
	
	@GetMapping("vmycourses")
	public ModelAndView vmycourses(HttpServletRequest request) {
	    HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

	    // Check if session is null or student is not logged in
	    if (session == null || session.getAttribute("teacher") == null) {
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("teacheradminlogin");  // Redirect to login page if not logged in
	        mv.addObject("message", "Please log in to access this page.");
	        return mv;
	    }

	    ModelAndView mv = new ModelAndView();
	    
	    // Retrieve the logged-in student
	    Teacher t = (Teacher) session.getAttribute("teacher");
	    
	    // Fetch the courses associated with the student using the student ID
	    List<CourseTeacher> courses = teacherService.getCourses(t.getId());
	    
	    // Add the list of courses to the ModelAndView object
	    mv.addObject("courses", courses);
	    
	    // Set the view to be rendered
	    mv.setViewName("vmycourses");  // JSP page name to render
	    
	    return mv;  // Return the ModelAndView
	}
	
	@PostMapping("vfeedback")
	public ModelAndView vfeedback(HttpServletRequest request) {
	    HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

	    // Check if session is null or student is not logged in
	    if (session == null || session.getAttribute("teacher") == null) {
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("teacheradminlogin");  // Redirect to login page if not logged in
	        mv.addObject("message", "Please log in to access this page.");
	        return mv;
	    }

	    ModelAndView mv = new ModelAndView();
	    
	    // Retrieve the logged-in student
	    Teacher t = (Teacher) session.getAttribute("teacher");
	    
	    String ccode = request.getParameter("ccode");
	    
	    
	    // Fetch the courses associated with the student using the student ID
	    List<Feedback> feedback = teacherService.getFeedback(t.getId(),ccode);
	    
	    // Add the list of courses to the ModelAndView object
	    mv.addObject("feedback", feedback);
	    
	    // Set the view to be rendered
	    mv.setViewName("vfeedback");  // JSP page name to render
	    
	    return mv;  // Return the ModelAndView
	}
	
}
