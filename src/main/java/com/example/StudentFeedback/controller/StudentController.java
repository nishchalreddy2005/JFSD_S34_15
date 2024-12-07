package com.example.StudentFeedback.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.StudentFeedback.entity.Course;
import com.example.StudentFeedback.entity.CourseTeacher;
import com.example.StudentFeedback.entity.CourseTeacherFeedback;
import com.example.StudentFeedback.entity.CourseTeacherStudent;
import com.example.StudentFeedback.entity.Feedback;
import com.example.StudentFeedback.entity.FeedbackId;
import com.example.StudentFeedback.entity.Student;
import com.example.StudentFeedback.entity.Teacher;
import com.example.StudentFeedback.repository.StudentRepository;
import com.example.StudentFeedback.service.StudentService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class StudentController 
{
	@Autowired
    private StudentService studentService;
	
	@GetMapping("/")
    public ModelAndView Home() {
    	ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		return mv;
	}
	
	@PostMapping("insertstudent")
    public ModelAndView registerStudent(HttpServletRequest request) {
        
    	String id = request.getParameter("sid");
    	String name = request.getParameter("sname");
		String gender = request.getParameter("sgender");
		String dob = request.getParameter("sdob");
		String email = request.getParameter("semail");
		String password = request.getParameter("spwd");
		String department = request.getParameter("sdepartment");
		String contact = request.getParameter("scontact");
		
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		student.setEmail(email);
		student.setPassword(password);
		student.setGender(gender);
		student.setDateofbirth(dob);
		student.setDepartment(department);
		student.setContactno(contact);
    	
    	ModelAndView mv = new ModelAndView();
    	Student existingStudent = studentService.checkIdUnique(student.getId());
        
        if (existingStudent != null) 
        {
            mv.addObject("message", "User Already Exist");
            mv.setViewName("studentlogin");
        }
        
        else 
        {
	        String message = studentService.studentRegistration(student);
	        mv.addObject("message", message);
	        mv.setViewName("studentlogin");
        }
        return mv;
    }
	
	@GetMapping("studentlogin")
	public ModelAndView studentlogin() 
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("studentlogin");
		return mv;
	}
	
	@PostMapping("checkstudentlogin")
    public ModelAndView checkStudentLogin(HttpServletRequest request) {
    	
    	ModelAndView mv = new ModelAndView();
		
		String semail = request.getParameter("semail");
		String spwd = request.getParameter("spwd");
		
        Student student = studentService.checkStudentLogin(semail,spwd);
        
        if (student != null) {
            HttpSession session = request.getSession(true);
            
            session.setAttribute("student", student);
			
			mv.setViewName("studenthome");
        } 
        else 
        {
            mv.setViewName("studentlogin");
			mv.addObject("message", "Login Failed");
        }
        return mv;
    }
	
	@GetMapping("studenthome")
    public ModelAndView studentHome(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

        if (session == null || session.getAttribute("student") == null) {
            // Session is null or does not contain a "student" attribute, meaning the user is logged out
            ModelAndView mv = new ModelAndView();
            mv.setViewName("studentlogin");
            mv.addObject("message", "Please log in to access this page.");
            return mv;
        }

        // If the session exists and contains a "student" attribute, allow access to the page
        ModelAndView mv = new ModelAndView();
        mv.setViewName("studenthome"); // or the view you want to load if logged in
        return mv;
    }
	
	@GetMapping("studentprofile")
	public ModelAndView studentprofile(HttpServletRequest request) 
	{
    	HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

        if (session == null || session.getAttribute("student") == null) {
            // Session is null or does not contain a "student" attribute, meaning the user is logged out
            ModelAndView mv = new ModelAndView();
            mv.setViewName("studentlogin");
            mv.addObject("message", "Please log in to access this page.");
            return mv;
        }
        
		ModelAndView mv = new ModelAndView();
		mv.setViewName("studentprofile");
		return mv;
	}
	
	@GetMapping("studentlogout")
    public ModelAndView studentlogout(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        // Invalidate the session to log out the student
        HttpSession session = request.getSession(false); // get the existing session if it exists
        if (session != null) {
            session.invalidate(); // destroys the session
        }
        
        mv.setViewName("home");
        return mv;
    }
	
	
	@GetMapping("registercourse")
	public ModelAndView registercourse(HttpServletRequest request) {
	    HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

	    if (session == null || session.getAttribute("student") == null) {
	        // Session is null or does not contain a "student" attribute, meaning the user is logged out
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("studentlogin");
	        mv.addObject("message", "Please log in to access this page.");
	        return mv;
	    }

	    // If the session exists and contains a "student" attribute, allow access to the page
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("courses");

	    // Retrieve the list of CourseTeacher entities
	    List<CourseTeacher> courseTeacherList = studentService.viewAll();

	    // Separate the courses and teachers into different lists
	    List<Course> courses = new ArrayList<>();
	    List<Teacher> teachers = new ArrayList<>();

	    for (CourseTeacher courseTeacher : courseTeacherList) {
	        // Add the course and teacher to their respective lists
	        if (!courses.contains(courseTeacher.getCourse())) {
	            courses.add(courseTeacher.getCourse());
	        }
	        if (!teachers.contains(courseTeacher.getTeacher())) {
	            teachers.add(courseTeacher.getTeacher());
	        }
	    }

	    // Add the separated lists to the ModelAndView
	    mv.addObject("courses", courses);
	    mv.addObject("teachers", teachers);

	    mv.setViewName("registercourse");
	    return mv;
	}
	
	@PostMapping("register")
	public ModelAndView register(HttpServletRequest request) {
		HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

	    if (session == null || session.getAttribute("student") == null) {
	        // Session is null or does not contain a "student" attribute, meaning the user is logged out
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("studentlogin");
	        mv.addObject("message", "Please log in to access this page.");
	        return mv;
	    }
	    
	 // If the session exists and contains a "student" attribute, allow access to the page
	    ModelAndView mv = new ModelAndView();
	    
	    int tid = Integer.parseInt(request.getParameter("teacherId"));
	    String ccode = request.getParameter("courseCode");
	    
	    Student s = (Student)session.getAttribute("student");
	    
	    Course c = studentService.findCourse(ccode);
	    
	    Teacher t = studentService.findTeacher(tid);
	    
	    if (c != null && t != null) {
            // Create a new CourseTeacher association entity
            CourseTeacherStudent association = new CourseTeacherStudent(c, t, s);

            // Save the association (use a service/repository for this)
            studentService.saveAssociation(association);

            mv.setViewName("registercourse");
            mv.addObject("message", "Teacher successfully assigned to the course!");
        } else {
            mv.setViewName("registercourse");
            mv.addObject("message", "Invalid course code or teacher ID.");
        }
	    
	    return mv;
	    
	}
	
	
	@GetMapping("mycourses")
	public ModelAndView courses(HttpServletRequest request) {
	    HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

	    // Check if session is null or student is not logged in
	    if (session == null || session.getAttribute("student") == null) {
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("studentlogin");  // Redirect to login page if not logged in
	        mv.addObject("message", "Please log in to access this page.");
	        return mv;
	    }

	    ModelAndView mv = new ModelAndView();
	    
	    // Retrieve the logged-in student
	    Student s = (Student) session.getAttribute("student");
	    
	    // Fetch the courses associated with the student using the student ID
	    List<CourseTeacherStudent> courses = studentService.getCourses(s.getId());
	    
	    // Add the list of courses to the ModelAndView object
	    mv.addObject("courses", courses);
	    
	    // Set the view to be rendered
	    mv.setViewName("mycourses");  // JSP page name to render
	    
	    return mv;  // Return the ModelAndView
	}

	@PostMapping("feedback")
	public ModelAndView feedback(HttpServletRequest request) 
	{
		HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

	    // Check if session is null or student is not logged in
	    if (session == null || session.getAttribute("student") == null) {
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("studentlogin");  // Redirect to login page if not logged in
	        mv.addObject("message", "Please log in to access this page.");
	        return mv;
	    }
	    
	    ModelAndView mv = new ModelAndView();
	    
	    int teid = Integer.parseInt(request.getParameter("tid"));
	    
	    
	    String ccode = request.getParameter("ccode");
	    
	    System.out.println(teid);
	    
	    
	    CourseTeacherFeedback ques = studentService.getQuestions(teid,ccode);
	    
	    if(ques!=null) {
	    
	    mv.addObject("ques", ques);
	    mv.setViewName("feedback");
	    }
	    else {
	    	String message = "No feedbacks";
	    	mv.addObject("message", message);
	    	mv.setViewName("studenthome");
	    }
	    
	    return mv;
	    
	    
	}
	
	@PostMapping("submitfeedback")
	public ModelAndView submitFeedback(HttpServletRequest request) {
	    HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

	    // Check if session is null or student is not logged in
	    if (session == null || session.getAttribute("student") == null) {
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("studentlogin");  // Redirect to login page if not logged in
	        mv.addObject("message", "Please log in to access this page.");
	        return mv;
	    }

	    ModelAndView mv = new ModelAndView();

	    // Parse and validate inputs
	    int q1 = Integer.parseInt(request.getParameter("q1"));
	    System.out.println(q1);
	    int q2 = Integer.parseInt(request.getParameter("q2"));
	    System.out.println(q2);
	    String q3 = request.getParameter("q3");
	    System.out.println(q3);
	    String q4 = request.getParameter("q4");
	    String q5 = request.getParameter("q5");

	    int teid = Integer.parseInt(request.getParameter("tid"));
	    String ccode = request.getParameter("ccode");

	    // Retrieve required entities
	    Teacher teacher = studentService.findTeacher(teid);
	    Course course = studentService.findCourse(ccode);
	    Student student = (Student) session.getAttribute("student");

	    // Initialize Feedback entity
	    Feedback feedback = new Feedback();
	    FeedbackId feedbackId = new FeedbackId(student.getId(), ccode, teacher.getId()); // Initialize composite key

	    feedback.setId(feedbackId); // Set the composite key
	    feedback.setStudent(student);
	    feedback.setTeacher(teacher);
	    feedback.setCourse(course);
	    feedback.setQuestion1(q1);
	    feedback.setQuestion2(q2);
	    feedback.setQuestion3(q3);
	    feedback.setQuestion4(q4);
	    feedback.setQuestion5(q5);

	    // Save feedback
	    studentService.saveFeedback(feedback);

	    mv.setViewName("mycourses"); // Redirect to feedback page
	    return mv;
	}
	
	
	

}
