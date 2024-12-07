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
<title>Home</title>
<link href="teacherhome.css" rel="stylesheet">
</head>
<body>

<%@include file="teachernav.jsp" %>

<div class="home-text">
    <p>Welcome, <%=t.getName()%></p>
</div>

</body>
</html>
