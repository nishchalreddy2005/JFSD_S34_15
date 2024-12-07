package com.example.StudentFeedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.StudentFeedback.entity.CourseTeacherStudent;
import com.example.StudentFeedback.entity.CourseTeacherStudentId;

@Repository
public interface CourseTeacherStudentRepository extends JpaRepository<CourseTeacherStudent, CourseTeacherStudentId> {

    @Query("SELECT cts FROM CourseTeacherStudent cts WHERE cts.student.id = :studentId")
    List<CourseTeacherStudent> findByStudentId(@Param("studentId") String studentId);
}

