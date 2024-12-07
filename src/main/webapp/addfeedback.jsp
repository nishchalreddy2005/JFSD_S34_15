<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form with Questions</title>
    <link rel="stylesheet" href="addfeedback.css">
</head>
<body>
<%@include file="teachernav.jsp" %>

    <div class="form-container">
        <h1>Feedback Form</h1>
        <form method="post" action="add">
            <div class="form-item">
                <label for="question1">Question 1:</label>
                <input type="text" id="question1" name="question1" placeholder="Enter your answer">
            </div>
            <div class="form-item">
                <label for="question2">Question 2:</label>
                <input type="text" id="question2" name="question2" placeholder="Enter your answer">
            </div>
            <div class="form-item">
                <label for="question3">Question 3:</label>
                <input type="text" id="question3" name="question3" placeholder="Enter your answer">
            </div>
            <div class="form-item">
                <label for="question4">Question 4:</label>
                <input type="text" id="question4" name="question4" placeholder="Enter your answer">
            </div>
            <div class="form-item">
                <label for="question5">Question 5:</label>
                <input type="text" id="question5" name="question5" placeholder="Enter your answer">
            </div>
            <input type="hidden" name="ccode" value="${ccode}">
            <button type="submit" class="submit-btn">Submit</button>
        </form>
    </div>
</body>
</html>
