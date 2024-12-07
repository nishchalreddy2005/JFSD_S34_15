package com.example.StudentFeedback.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FeedbackId implements Serializable {

    private String studentId;
    private String courseCode;
    private int teacherId;  // Changed to int

    // Constructors
    public FeedbackId() {}

    public FeedbackId(String studentId, String courseCode, int teacherId) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.teacherId = teacherId;
    }

    // Getters and setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public int getTeacherId() { return teacherId; }  // Changed to int
    public void setTeacherId(int teacherId) { this.teacherId = teacherId; }

    // hashCode and equals methods to define the composite key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackId that = (FeedbackId) o;
        return teacherId == that.teacherId && 
               studentId.equals(that.studentId) && 
               courseCode.equals(that.courseCode);
    }

    @Override
    public int hashCode() {
        return studentId.hashCode() + courseCode.hashCode() + Integer.hashCode(teacherId);
    }
}
