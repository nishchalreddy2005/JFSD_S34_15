<%@page import="com.example.StudentFeedback.entity.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Admin a = (Admin)session.getAttribute("admin");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="studentprofile.css" rel="stylesheet">
</head>
<body>
<%@include file="adminnav.jsp" %>
	<div class="profile-container">
        <div class="header">
            <img src="profile-pic.jpg" alt="Profile Picture" class="profile-pic">
            <h1 class="Name"><%=a.getUsername()%></h1>
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
                <label for="id-number">Username:</label>
                <input type="text" id="id-number" value="<%=a.getUsername()%>" readonly>

                <label for="name">Password:</label>
                <input type="text" id="name" value="<%=a.getPassword()%>" readonly>
            </div>
        </div>
    </div>
</body>
</html>