<%@page import="com.example.StudentFeedback.entity.Teacher"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Teacher t = (Teacher)session.getAttribute("teacher");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="studentprofile.css" rel="stylesheet">
</head>
<body>
<%@include file="teachernav.jsp" %>
	<div class="profile-container">
        <div class="header">
            <img src="profile-pic.jpg" alt="Profile Picture" class="profile-pic">
            <h1 class="name"><%=t.getName()%></h1>
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
                <input type="text" id="id-number" value="<%=t.getId()%>" readonly>

                <label for="name">Name:</label>
                <input type="text" id="name" value="<%=t.getName()%>" readonly>

                <label for="email">Email:</label>
                <input type="text" id="email" value="<%=t.getEmail()%>" readonly>

                <label for="branch">Department:</label>
                <input type="text" id="department" value="<%=t.getDepartment()%>" readonly>

                <label for="dob">Gender:</label>
                <input type="text" id="dob" value="<%=t.getGender()%>" readonly>

                <label for="gender">Qualification:</label>
                <input type="text" id="gender" value="<%=t.getQualification()%>" readonly>
                
                <label for="gender">Contact No:</label>
                <input type="text" id="gender" value="<%=t.getContactno()%>" readonly>
            </div>
        </div>
    </div>
</body>
</html>