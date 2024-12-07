package com.example.StudentFeedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.StudentFeedback.entity.Feedback;
import com.example.StudentFeedback.entity.FeedbackId;

public interface FeedbackRepository extends JpaRepository<Feedback, FeedbackId>
{
	@Query("SELECT f FROM Feedback f WHERE f.teacher.id = :teacherId AND f.course.code = :courseCode")
    List<Feedback> findByTeacherIdAndCourseCode(@Param("teacherId") Integer teacherId, @Param("courseCode") String courseCode);
}
