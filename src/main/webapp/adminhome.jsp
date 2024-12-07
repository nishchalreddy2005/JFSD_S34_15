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
<title>Home</title>
<link href="adminhome.css" rel="stylesheet">
</head>
<body>

<%@include file="adminnav.jsp" %>

<div class="home-text">
    <p>Welcome, <%=a.getUsername()%></p>
</div>

</body>
</html>
