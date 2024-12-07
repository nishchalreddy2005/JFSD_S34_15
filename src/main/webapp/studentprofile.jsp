<%@page import="com.example.StudentFeedback.entity.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Student s = (Student)session.getAttribute("student");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="studentprofile.css" rel="stylesheet">
</head>
<body>
<%@include file="studentnav.jsp" %>
	<div class="profile-container">
        <div class="header">
            <img src="profile-pic.jpg" alt="Profile Picture" class="profile-pic">
            <h1 class="name"><%=s.getName()%></h1>
            <p class="title">Software Engineer | Hobbyist Photographer</p>
            <div class="contact">
                <a href="mailto:john.doe@example.com">Email</a>
                <a href="tel:+1234567890">Call</a>
                <a href="#" class="social-icon">LinkedIn</a>
                <a href="#" class="social-icon">GitHub</a>
            </div>
        </div>
        <div class="tabs">
            <button class="tab active">Overview</button>
        </div>
        <div class="content">
            <h2>Personal Information</h2>
            <div class="profile-info">
                <label for="id-number">ID Number:</label>
                <input type="text" id="id-number" value="<%=s.getId()%>" readonly>

                <label for="name">Name:</label>
                <input type="text" id="name" value="<%=s.getName()%>" readonly>

                <label for="email">Email:</label>
                <input type="text" id="email" value="<%=s.getEmail()%>" readonly>

                <label for="branch">Department:</label>
                <input type="text" id="department" value="<%=s.getDepartment()%>" readonly>

                <label for="dob">Date of Birth:</label>
                <input type="text" id="dob" value="<%=s.getDateofbirth()%>" readonly>

                <label for="gender">Gender:</label>
                <input type="text" id="gender" value="<%=s.getGender()%>" readonly>
                
                <label for="gender">Contact No:</label>
                <input type="text" id="gender" value="<%=s.getContactno()%>" readonly>
            </div>
        </div>
    </div>
</body>
</html>