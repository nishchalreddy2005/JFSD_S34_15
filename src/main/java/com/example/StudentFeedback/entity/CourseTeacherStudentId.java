package com.example.StudentFeedback.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CourseTeacherStudentId implements Serializable {

    private String courseCode;
    private int teacherId;
    private String studentId; // Changed to String

    // Default constructor
    public CourseTeacherStudentId() {}

    // Parameterized constructor
    public CourseTeacherStudentId(String courseCode, int teacherId, String studentId) {
        this.courseCode = courseCode;
        this.teacherId = teacherId;
        this.studentId = studentId;
    }

    // Getters and setters
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public int getTeacherId() { return teacherId; }
    public void setTeacherId(int teacherId) { this.teacherId = teacherId; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseTeacherStudentId that = (CourseTeacherStudentId) o;
        return teacherId == that.teacherId &&
               Objects.equals(courseCode, that.courseCode) &&
               Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, teacherId, studentId);
    }
}
