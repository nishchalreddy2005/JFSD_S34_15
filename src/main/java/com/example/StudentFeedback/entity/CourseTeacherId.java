package com.example.StudentFeedback.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CourseTeacherId implements Serializable {

    private String courseCode;
    private Integer teacherId;

    // Default constructor
    public CourseTeacherId() {}

    // Parameterized constructor
    public CourseTeacherId(String courseCode, Integer teacherId) {
        this.courseCode = courseCode;
        this.teacherId = teacherId;
    }

    // Getters and setters
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public Integer getTeacherId() { return teacherId; }
    public void setTeacherId(Integer teacherId) { this.teacherId = teacherId; }

    // hashCode and equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseTeacherId that = (CourseTeacherId) o;
        return Objects.equals(courseCode, that.courseCode) && Objects.equals(teacherId, that.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, teacherId);
    }
}
