package com.example.StudentFeedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.StudentFeedback.entity.CourseTeacherFeedback;

@Repository
public interface CourseTeacherFeedbackRepository extends JpaRepository<CourseTeacherFeedback, Integer> {

    @Query("SELECT ctf FROM CourseTeacherFeedback ctf WHERE ctf.teacher.id = :teacherId AND ctf.course.code = :courseCode")
    CourseTeacherFeedback findByTeacherIdAndCourseCode(@Param("teacherId") Integer teacherId, @Param("courseCode") String courseCode);

}


