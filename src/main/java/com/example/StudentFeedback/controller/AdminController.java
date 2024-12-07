package com.example.StudentFeedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.StudentFeedback.entity.Admin;
import com.example.StudentFeedback.entity.Course;
import com.example.StudentFeedback.entity.CourseTeacher;
import com.example.StudentFeedback.entity.Feedback;
import com.example.StudentFeedback.entity.Student;
import com.example.StudentFeedback.entity.Teacher;
import com.example.StudentFeedback.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class AdminController 
{
	@Autowired
    private AdminService adminService;
	
	
	@PostMapping("checkadminlogin")
    public ModelAndView loginAdmin(HttpServletRequest request) {
    	
    	ModelAndView mv = new ModelAndView();
		
		String auname = request.getParameter("auname");
		String apwd = request.getParameter("apwd");
		
        Admin admin = adminService.checkAdminLogin(auname,apwd);

        if (admin != null) {
			HttpSession session = request.getSession();
			
			session.setAttribute("admin", admin);
			
        	mv.setViewName("adminhome");
        } else {
            mv.addObject("message","Login Failed");
            mv.setViewName("teacheradminlogin");
        }
        return mv;
    }
	
	@GetMapping("adminhome")
    public ModelAndView adminHome(HttpServletRequest request) {
    	HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

        if (session == null || session.getAttribute("admin") == null) {
            // Session is null or does not contain a "admin" attribute, meaning the user is logged out
            ModelAndView mv = new ModelAndView();
            mv.setViewName("teacheradminlogin");
            mv.addObject("message", "Please log in to access this page.");
            return mv;
        }
    	ModelAndView mv = new ModelAndView();
		mv.setViewName("adminhome");
		return mv;
    }
	
	@GetMapping("adminlogout")
    public ModelAndView adminlogout(HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView();
    	
    	 // Invalidate the session to log out the Admin
        HttpSession session = request.getSession(false); // get the existing session if it exists
        if (session != null) {
            session.invalidate(); // destroys the session
        }
    	
		mv.setViewName("home");
		return mv;
    }
	
	@GetMapping("adminlogin")
	public ModelAndView loginClub(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("teacheradminlogin");
		return mv;
	}
	
	@PostMapping("addcourse")
	public ModelAndView addcourse(HttpServletRequest request) 
	{
		ModelAndView mv = new ModelAndView();
		
		String ccode = request.getParameter("ccode");
		String cname = request.getParameter("cname");
		String cdepartment = request.getParameter("cdepartment");
		
		Course course = new Course();
		course.setCode(ccode);
		course.setName(cname);
		course.setDepartment(cdepartment);
		
		Course existingCourse = adminService.checkIdUnique(course.getCode());
		
		if(existingCourse!=null) {
			mv.addObject("message", "Course Already Exist");
            mv.setViewName("newcourse");
		}
		else {
			String message = adminService.addCourse(course);
	        mv.addObject("message", message);
	        mv.setViewName("newcourse");
		}
		
		return mv;
	}
	
	@GetMapping("newcourse")
	public ModelAndView newcourse(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("newcourse");
		return mv;
	}
	
	@GetMapping("courses")
	public ModelAndView courses(HttpServletRequest request) {
		HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

        if (session == null || session.getAttribute("admin") == null) {
            // Session is null or does not contain a "student" attribute, meaning the user is logged out
            ModelAndView mv = new ModelAndView();
            mv.setViewName("teacheradminlogin");
            mv.addObject("message", "Please log in to access this page.");
            return mv;
        }
        
     // If the session exists and contains a "student" attribute, allow access to the page
        ModelAndView mv = new ModelAndView();
        mv.setViewName("courses");
		List<Course> courses = adminService.viewAll();
		mv.addObject("courselist", courses);
		List<Teacher> teachers = adminService.viewTeacher();
		mv.addObject("teacherlist", teachers);
		return mv;
	}
	
	@PostMapping("addteacher")
	public ModelAndView addteacher(HttpServletRequest request) {
	    // Extract course code and teacher ID from the request
	    String courseCode = request.getParameter("courseCode");
	    String teacherId = request.getParameter("teacherId");

	    // Logging for debugging (optional)
	    System.out.println("Course Code: " + courseCode);
	    System.out.println("Teacher ID: " + teacherId);

	    // Initialize the ModelAndView
	    ModelAndView mv = new ModelAndView();

	    // Perform the logic to associate the course and teacher
	    try {
	        // Fetch the course and teacher entities (adjust this based on your service/repository)
	        Course course = adminService.findCourseByCode(courseCode);
	        Teacher teacher = adminService.findTeacherById(Integer.parseInt(teacherId));

	        if (course != null && teacher != null) {
	            // Create a new CourseTeacher association entity
	            CourseTeacher association = new CourseTeacher(course, teacher);

	            // Save the association (use a service/repository for this)
	            adminService.saveAssociation(association);

	            mv.setViewName("courses");
	            mv.addObject("message", "Teacher successfully assigned to the course!");
	        } else {
	            mv.setViewName("courses");
	            mv.addObject("message", "Invalid course code or teacher ID.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        mv.setViewName("courses");
	        mv.addObject("message", "An error occurred while assigning the teacher.");
	    }

	    return mv;
	}
	
	@GetMapping("adminprofile")
	public ModelAndView adminprofile(HttpServletRequest request) 
	{
    	HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

        if (session == null || session.getAttribute("admin") == null) {
            // Session is null or does not contain a "student" attribute, meaning the user is logged out
            ModelAndView mv = new ModelAndView();
            mv.setViewName("teacheradminlogin");
            mv.addObject("message", "Please log in to access this page.");
            return mv;
        }
        
		ModelAndView mv = new ModelAndView();
		mv.setViewName("adminprofile");
		return mv;
	}
	
	@GetMapping("acourses")
	public ModelAndView acourses(HttpServletRequest request) {
	    HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

	    // Check if session is null or student is not logged in
	    if (session == null || session.getAttribute("admin") == null) {
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("teacheradminlogin");  // Redirect to login page if not logged in
	        mv.addObject("message", "Please log in to access this page.");
	        return mv;
	    }

	    ModelAndView mv = new ModelAndView();
	    
	    // Retrieve the logged-in student
	    Admin a = (Admin) session.getAttribute("admin");
	    
	    // Fetch the courses associated with the student using the student ID
	    List<CourseTeacher> courses = adminService.getCourses();
	    
	    // Add the list of courses to the ModelAndView object
	    mv.addObject("courses", courses);
	    
	    // Set the view to be rendered
	    mv.setViewName("acourses");  // JSP page name to render
	    
	    return mv;  // Return the ModelAndView
	}
	
	@PostMapping("afeedback")
	public ModelAndView afeedback(HttpServletRequest request) {
	    HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

	    // Check if session is null or student is not logged in
	    if (session == null || session.getAttribute("admin") == null) {
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("teacheradminlogin");  // Redirect to login page if not logged in
	        mv.addObject("message", "Please log in to access this page.");
	        return mv;
	    }

	    ModelAndView mv = new ModelAndView();
	    
	    // Retrieve the logged-in student
	    
	    int tid = Integer.parseInt(request.getParameter("tid"));
	    
	    System.out.println(tid);
	    
	    String ccode = request.getParameter("ccode");
	    
	    
	    // Fetch the courses associated with the student using the student ID
	    List<Feedback> feedback = adminService.getFeedback(tid,ccode);
	    
	    // Add the list of courses to the ModelAndView object
	    mv.addObject("feedback", feedback);
	    
	    // Set the view to be rendered
	    mv.setViewName("afeedback");  // JSP page name to render
	    
	    return mv;  // Return the ModelAndView
	}
	
	@GetMapping("students")
	public ModelAndView students(HttpServletRequest request) 
	{
		HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

	    // Check if session is null or student is not logged in
	    if (session == null || session.getAttribute("admin") == null) {
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("teacheradminlogin");  // Redirect to login page if not logged in
	        mv.addObject("message", "Please log in to access this page.");
	        return mv;
	    }
	    
	    ModelAndView mv = new ModelAndView();
	    
	    List<Student> students = adminService.getStudents();
	    
	    mv.addObject("students", students);
	    
	    mv.setViewName("students");
	    
	    return mv;
	    
	    
	}
	
	@PostMapping("deletestudent")
	public ModelAndView deletestudent(HttpServletRequest request) 
	{
		HttpSession session = request.getSession(false); // Get existing session, but don't create a new one if it doesn't exist

	    // Check if session is null or student is not logged in
	    if (session == null || session.getAttribute("admin") == null) {
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("teacheradminlogin");  // Redirect to login page if not logged in
	        mv.addObject("message", "Please log in to access this page.");
	        return mv;
	    }
	    
	    ModelAndView mv = new ModelAndView();
	    
	    String id = request.getParameter("studentId");
	    
	    adminService.deleteStudent(id);
	    
	    mv.setViewName("adminhome");
	    
	    return mv;
	}
	
}
