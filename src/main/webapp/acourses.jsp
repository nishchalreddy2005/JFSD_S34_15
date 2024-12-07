<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Courses</title>
    <%@ include file="adminnav.jsp" %>
</head>
<body style="background-color: #eeeeee; color: #282828; padding: 20px; font-family: Arial, sans-serif;">

    <div class="page-container" style="display: flex; justify-content: center; align-items: center; flex-direction: column; min-height: 100vh; background-color: white; color: #282828; text-align: center;">
        <h1 style="font-size: 2.5rem; margin-bottom: 20px;">My Courses</h1>

        <!-- Check if courses exist -->
        <c:if test="${not empty courses}">
            <div class="events" style="display: flex; justify-content: center; gap: 20px; flex-wrap: wrap; animation: fadeIn 1.5s ease-in-out forwards; align-items: center; min-height: 100vh; margin: 0;">
                <!-- Loop through the courses -->
                <c:forEach var="course" items="${courses}">
                    <div class="event-card" style="position: relative; background-size: cover; background-position: center; width: 300px; height: 400px; border-radius: 12px; overflow: hidden; box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3); transition: transform 0.3s ease, box-shadow 0.3s ease; cursor: pointer; animation: zoomIn 1s ease forwards;">
                        <div class="event-content" style="position: absolute; bottom: 0; left: 0; right: 0; color: #57B894; text-align: center; padding: 30px; background: white; display: flex; flex-direction: column; justify-content: center; align-items: center;">
                            <h4>${course.course.name}</h4> <!-- Course Name -->
                            <p>Course Code: ${course.course.code}</p> <!-- Course Code -->

                            <!-- Form to submit the selected teacher for the course -->
                            <form method="POST" action="afeedback">
                                <input type="hidden" name="ccode" value="${course.course.code}">
                                <input type="hidden" name="tid" value="${course.teacher.id}">
                                <button type="submit" class="book-button" style="margin-top: 10px; padding: 8px 15px; background-color: #57B894; color: white; border: none; border-radius: 5px; cursor: pointer; transition: background-color 0.3s ease; animation: popIn 0.7s ease forwards;">
                                    View Feedback
                                </button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <!-- If no courses -->
        <c:if test="${empty courses}">
            <p>You are not enrolled in any courses.</p>
        </c:if>

    </div>

</body>
</html>
