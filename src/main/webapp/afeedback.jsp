<%@page import="com.example.StudentFeedback.entity.Feedback"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Summary</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f7fc;
        }
        header {
            background-color: #004d99;
            color: white;
            padding: 20px;
            text-align: center;
        }
        .container {
            padding: 20px;
        }
        h1 {
            font-size: 2.5em;
            margin-bottom: 20px;
        }
        .charts-section {
            margin-top: 40px;
        }
        .bar-chart-container {
            display: flex;
            justify-content: center;
            margin-bottom: 40px;
        }
        .pie-charts-container {
            display: flex;
            justify-content: space-around;
            gap: 40px;
            flex-wrap: wrap; /* Ensures the charts wrap on smaller screens */
            margin-top: 20px;
        }
        .pie-charts-container div {
            text-align: center;
            flex: 1;
            max-width: 320px; /* Ensures the charts do not stretch too wide */
        }
        canvas {
            max-width: 100%;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <header>
        <h1>Feedback Summary</h1>
    </header>

    <div class="container">
        <!-- Bar Chart Section for Questions 1 and 2 -->
        <div class="bar-chart-container">
            <canvas id="feedbackChart" width="600" height="400"></canvas>
        </div>

        <!-- Pie Charts Section for Questions 3, 4, and 5 -->
        <div class="pie-charts-container">
            <!-- Question 3 Word Frequency Pie Chart -->
            <div>
                <h3>Question 3</h3>
                <canvas id="feedbackTextChartQ3" width="300" height="300"></canvas>
            </div>

            <!-- Question 4 Word Frequency Pie Chart -->
            <div>
                <h3>Question 4</h3>
                <canvas id="feedbackTextChartQ4" width="300" height="300"></canvas>
            </div>

            <!-- Question 5 Word Frequency Pie Chart -->
            <div>
                <h3>Question 5</h3>
                <canvas id="feedbackTextChartQ5" width="300" height="300"></canvas>
            </div>
        </div>
    </div>

    <script>
        // Java code will pass the feedback data as a JSON object
        const feedbackData = [
            <%-- Dynamically generate JavaScript array from the "feedback" list --%>
            <% for (Feedback fb : (List<Feedback>) request.getAttribute("feedback")) { %>
                {
                    question1: <%= fb.getQuestion1() %>,
                    question2: <%= fb.getQuestion2() %>,
                    question3: "<%= fb.getQuestion3() %>",
                    question4: "<%= fb.getQuestion4() %>",
                    question5: "<%= fb.getQuestion5() %>"
                },
            <% } %>
        ];

        // Extract numerical ratings for each question
        const question1Ratings = feedbackData.map(fb => fb.question1);
        const question2Ratings = feedbackData.map(fb => fb.question2);

        // Extract text responses for questions 3, 4, and 5
        const question3Responses = feedbackData.map(fb => fb.question3);
        const question4Responses = feedbackData.map(fb => fb.question4);
        const question5Responses = feedbackData.map(fb => fb.question5);

        // Calculate averages for numerical questions
        const avgQ1 = (question1Ratings.reduce((a, b) => a + b, 0) / question1Ratings.length).toFixed(2);
        const avgQ2 = (question2Ratings.reduce((a, b) => a + b, 0) / question2Ratings.length).toFixed(2);

        // Prepare data for text feedback visualization (e.g., counts of responses)
        const getWordCounts = (responses) => {
            const wordMap = {};
            responses.forEach(response => {
                response.split(/\s+/).forEach(word => {
                    const lowerWord = word.toLowerCase();
                    wordMap[lowerWord] = (wordMap[lowerWord] || 0) + 1;
                });
            });
            return wordMap;
        };

        const question3WordCounts = getWordCounts(question3Responses);
        const question4WordCounts = getWordCounts(question4Responses);
        const question5WordCounts = getWordCounts(question5Responses);

        // Bar Chart for Numerical Ratings (Question 1 and Question 2)
        const ctx = document.getElementById('feedbackChart').getContext('2d');
        const feedbackChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Question 1', 'Question 2'], // Questions
                datasets: [
                    {
                        label: 'Average Ratings',
                        data: [avgQ1, avgQ2], // Average ratings
                        backgroundColor: [
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)'
                        ],
                        borderColor: [
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)'
                        ],
                        borderWidth: 1
                    }
                ]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: 'Average Feedback Ratings'
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });

        // Pie Chart for Question 3 Text Responses
        const ctxQ3 = document.getElementById('feedbackTextChartQ3').getContext('2d');
        const feedbackTextChartQ3 = new Chart(ctxQ3, {
            type: 'pie',
            data: {
                labels: Object.keys(question3WordCounts), // Words
                datasets: [
                    {
                        label: 'Question 3 Word Frequency',
                        data: Object.values(question3WordCounts), // Counts
                        backgroundColor: Object.keys(question3WordCounts).map((_, index) => 
                            `rgba(${(index * 30) % 255}, ${(index * 50) % 255}, ${(index * 80) % 255}, 0.7)`
                        ),
                        borderColor: 'rgba(0, 0, 0, 0.2)',
                        borderWidth: 1
                    }
                ]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: 'Word Frequency in Question 3'
                    }
                }
            }
        });

        // Pie Chart for Question 4 Text Responses
        const ctxQ4 = document.getElementById('feedbackTextChartQ4').getContext('2d');
        const feedbackTextChartQ4 = new Chart(ctxQ4, {
            type: 'pie',
            data: {
                labels: Object.keys(question4WordCounts), // Words
                datasets: [
                    {
                        label: 'Question 4 Word Frequency',
                        data: Object.values(question4WordCounts), // Counts
                        backgroundColor: Object.keys(question4WordCounts).map((_, index) => 
                            `rgba(${(index * 60) % 255}, ${(index * 100) % 255}, ${(index * 150) % 255}, 0.7)`
                        ),
                        borderColor: 'rgba(0, 0, 0, 0.2)',
                        borderWidth: 1
                    }
                ]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: 'Word Frequency in Question 4'
                    }
                }
            }
        });

        // Pie Chart for Question 5 Text Responses
        const ctxQ5 = document.getElementById('feedbackTextChartQ5').getContext('2d');
        const feedbackTextChartQ5 = new Chart(ctxQ5, {
            type: 'pie',
            data: {
                labels: Object.keys(question5WordCounts), // Words
                datasets: [
                    {
                        label: 'Question 5 Word Frequency',
                        data: Object.values(question5WordCounts), // Counts
                        backgroundColor: Object.keys(question5WordCounts).map((_, index) => 
                            `rgba(${(index * 90) % 255}, ${(index * 120) % 255}, ${(index * 180) % 255}, 0.7)`
                        ),
                        borderColor: 'rgba(0, 0, 0, 0.2)',
                        borderWidth: 1
                    }
                ]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    title: {
                        display: true,
                        text: 'Word Frequency in Question 5'
                    }
                }
            }
        });
    </script>
</body>
</html>
