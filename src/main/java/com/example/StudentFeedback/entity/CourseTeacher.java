package com.example.StudentFeedback.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course_teacher")
public class CourseTeacher {

    @EmbeddedId
    private CourseTeacherId id;

    @ManyToOne
    @MapsId("courseCode")
    @JoinColumn(name = "course_code")
    private Course course;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    // Constructors
    public CourseTeacher() {}

    public CourseTeacher(Course course, Teacher teacher) {
        this.course = course;
        this.teacher = teacher;
        this.id = new CourseTeacherId(course.getCode(), teacher.getId());
    }

    // Getters and setters
    public CourseTeacherId getId() { return id; }
    public void setId(CourseTeacherId id) { this.id = id; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }
}
