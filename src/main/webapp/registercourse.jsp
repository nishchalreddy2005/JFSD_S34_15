<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Assign Teacher to Course</title>
    <link rel="stylesheet" href="courses.css">
    <%@ include file="studentnav.jsp" %>
</head>
<body>
    <div class="container">
        <c:forEach var="course" items="${courses}">
            <!-- Course Section -->
            <section class="events">
                <!-- Event Card -->
                <div class="event-card" style="background-image: url('concert.jpg');"> 
                    <div class="event-content">
                        <h4>${course.name}</h4> <!-- Course Name -->
                        <p>Course Code: ${course.code}</p> <!-- Course Code -->

                        <!-- Form to assign teacher to the course -->
                        <form method="POST" action="register">
                            <!-- Hidden field to pass the course code -->
                            <input type="hidden" name="courseCode" value="${course.code}">

                            <!-- Teacher Name Dropdown -->
                            <label for="teacher-select-${course.code}" style="color: #f5f5dc;">Select Teacher:</label>
                            <select id="teacher-select-${course.code}" name="teacherId" class="dropdown" required>
                                <option value="" disabled selected>Choose a teacher</option>
                                <c:forEach var="teacher" items="${teachers}">
                                    <option value="${teacher.id}">${teacher.name}</option>
                                </c:forEach>
                            </select>

                            <!-- Submit button -->
                            <button type="submit" class="book-button">Assign Teacher</button>
                        </form>
                    </div>
                </div>
            </section>
        </c:forEach>
    </div>
</body>
</html>
