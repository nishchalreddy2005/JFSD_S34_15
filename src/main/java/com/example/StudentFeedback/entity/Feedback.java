package com.example.StudentFeedback.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {

    @EmbeddedId
    private FeedbackId id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("courseCode")
    @JoinColumn(name = "course_code")
    private Course course;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "question_1", nullable = false)
    private int question1;

    @Column(name = "question_2", nullable = false)
    private int question2;

    @Column(name = "question_3", nullable = false, length = 100)
    private String question3;

    @Column(name = "question_4", nullable = false, length = 100)
    private String question4;

    @Column(name = "question_5", nullable = false, length = 500)
    private String question5;

    // Constructors
    public Feedback() {}

    public Feedback(Student student, Course course, Teacher teacher, int question1, int question2, String question3, String question4, String question5) {
        this.student = student;
        this.course = course;
        this.teacher = teacher;
        this.id = new FeedbackId(student.getId(), course.getCode(), teacher.getId());
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
    }

    // Getters and setters
    public FeedbackId getId() { return id; }
    public void setId(FeedbackId id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    public int getQuestion1() { return question1; }
    public void setQuestion1(int question1) { this.question1 = question1; }

    public int getQuestion2() { return question2; }
    public void setQuestion2(int question2) { this.question2 = question2; }

    public String getQuestion3() { return question3; }
    public void setQuestion3(String question3) { this.question3 = question3; }

    public String getQuestion4() { return question4; }
    public void setQuestion4(String question4) { this.question4 = question4; }

    public String getQuestion5() { return question5; }
    public void setQuestion5(String question5) { this.question5 = question5; }
}
