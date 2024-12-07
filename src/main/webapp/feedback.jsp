<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course Review with Stars and Text Input</title>
    <link rel="stylesheet" href="feedback.css">
</head>
<body>
<%@ include file="studentnav.jsp" %>
    <div class="review-container">
        <h1>Course Review</h1>
        <form class="review-form" method="post" action="submitfeedback">
            <!-- Question 1 -->
            <div class="review-item">
                <label for="q1-1">1. ${ques.question1}</label>
                <div class="star-rating" style="display: flex; flex-direction: row-reverse;">
                    <input type="radio" name="q1" id="q1-5" value="5"><label for="q1-5">&#9733;</label>
                    <input type="radio" name="q1" id="q1-4" value="4"><label for="q1-4">&#9733;</label>
                    <input type="radio" name="q1" id="q1-3" value="3"><label for="q1-3">&#9733;</label>
                    <input type="radio" name="q1" id="q1-2" value="2"><label for="q1-2">&#9733;</label>
                    <input type="radio" name="q1" id="q1-1" value="1"><label for="q1-1">&#9733;</label>
                </div>
            </div>

            <!-- Question 2 -->
            <div class="review-item">
                <label for="q2-1">2. ${ques.question2}</label>
                <div class="star-rating" style="display: flex; flex-direction: row-reverse;">
                    <input type="radio" name="q2" id="q2-5" value="5"><label for="q2-5">&#9733;</label>
                    <input type="radio" name="q2" id="q2-4" value="4"><label for="q2-4">&#9733;</label>
                    <input type="radio" name="q2" id="q2-3" value="3"><label for="q2-3">&#9733;</label>
                    <input type="radio" name="q2" id="q2-2" value="2"><label for="q2-2">&#9733;</label>
                    <input type="radio" name="q2" id="q2-1" value="1"><label for="q2-1">&#9733;</label>
                </div>
            </div>

            <!-- Question 3 (Text input) -->
            <div class="review-item">
                <label for="q3">3. ${ques.question3}</label>
                <textarea name="q3" id="q3" rows="4" placeholder="Type your answer here..."></textarea>
            </div>

            <!-- Question 4 (Text input) -->
            <div class="review-item">
                <label for="q4">4. ${ques.question4}</label>
                <textarea name="q4" id="q4" rows="4" placeholder="Type your answer here..."></textarea>
            </div>
            
            <!-- Question 5 (Text input) -->
            <div class="review-item">
                <label for="q5">5. ${ques.question5}</label>
                <textarea name="q5" id="q5" rows="4" placeholder="Type your answer here..."></textarea>
            </div>

            <!-- Hidden Inputs -->
            <input type="hidden" name="ccode" value="${ques.course.code}">
            <input type="hidden" name="tid" value="${ques.teacher.id}">

            <!-- Submit Button -->
            <button type="submit" class="submit-btn">Submit Review</button>
        </form>
    </div>
</body>
</html>
