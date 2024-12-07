package com.example.StudentFeedback.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course_teacher_student")
public class CourseTeacherStudent {

    @EmbeddedId
    private CourseTeacherStudentId id;

    @ManyToOne
    @MapsId("courseCode")
    @JoinColumn(name = "course_code")
    private Course course;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    // Constructors
    public CourseTeacherStudent() {}

    public CourseTeacherStudent(Course course, Teacher teacher, Student student) {
        this.course = course;
        this.teacher = teacher;
        this.student = student;
        this.id = new CourseTeacherStudentId(course.getCode(), teacher.getId(), student.getId());
    }

    // Getters and setters
    public CourseTeacherStudentId getId() { return id; }
    public void setId(CourseTeacherStudentId id) { this.id = id; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
}
