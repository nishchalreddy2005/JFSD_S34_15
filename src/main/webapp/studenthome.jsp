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
<title>Home</title>
<link href="studenthome.css" rel="stylesheet">
</head>
<body>

<%@include file="studentnav.jsp" %>

<div class="home-text">
    <p>Welcome, <%=s.getName()%></p>
</div>

</body>
</html>
