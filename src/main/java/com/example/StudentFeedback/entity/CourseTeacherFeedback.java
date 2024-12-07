package com.example.StudentFeedback.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course_teacher_feedback")
public class CourseTeacherFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_code", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @Column(name = "question_1", nullable = false)
    private String question1;

    @Column(name = "question_2", nullable = false)
    private String question2;

    @Column(name = "question_3", nullable = false)
    private String question3;

    @Column(name = "question_4", nullable = false)
    private String question4;

    @Column(name = "question_5", nullable = false)
    private String question5;

    // Constructors
    public CourseTeacherFeedback() {}

    public CourseTeacherFeedback(Course course, Teacher teacher, String question1, String question2, String question3, String question4, String question5) {
        this.course = course;
        this.teacher = teacher;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    public String getQuestion1() { return question1; }
    public void setQuestion1(String question1) { this.question1 = question1; }

    public String getQuestion2() { return question2; }
    public void setQuestion2(String question2) { this.question2 = question2; }

    public String getQuestion3() { return question3; }
    public void setQuestion3(String question3) { this.question3 = question3; }

    public String getQuestion4() { return question4; }
    public void setQuestion4(String question4) { this.question4 = question4; }

    public String getQuestion5() { return question5; }
    public void setQuestion5(String question5) { this.question5 = question5; }
}
