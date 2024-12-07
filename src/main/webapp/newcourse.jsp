<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teacher Credentials Form</title>
    <link rel="stylesheet" href="newcourse.css">
</head>
<body>
	<%@include file="adminnav.jsp" %>
    <div class="form-container">
        <h1>Course Entry Form</h1>
        <form class="course-form" method="post" action="addcourse">
            <!-- Course Name -->
            <div class="form-group">
                <label for="course-name">Course Name:</label>
                <input type="text" id="course-name" name="cname" placeholder="Enter Course Name" required>
            </div>
    
            <!-- Course Code -->
            <div class="form-group">
                <label for="course-code">Course Code:</label>
                <input type="text" id="course-code" name="ccode" placeholder="Enter Course Code" required>
            </div>
    
            <!-- Branch -->
            <div class="form-group">
                <label for="course-branch">Branch:</label>
                <select id="course-branch" name="cdepartment" required>
                    <option value="" disabled selected>Select Branch</option>
                    <option value="science">Science</option>
                    <option value="arts">Arts</option>
                    <option value="commerce">Commerce</option>
                    <option value="engineering">Engineering</option>
                    <option value="medical">Medical</option>
                </select>
            </div>
    
            <button type="submit" class="submit-btn">Submit</button>
        </form>
    </div>
    
</body>
</html>
