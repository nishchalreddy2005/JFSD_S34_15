package com.example.StudentFeedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.StudentFeedback.entity.CourseTeacher;
import com.example.StudentFeedback.entity.CourseTeacherId;

@Repository
public interface CourseTeacherRepository extends JpaRepository<CourseTeacher, CourseTeacherId>
{
	@Query("SELECT ct FROM CourseTeacher ct WHERE ct.teacher.id = :teacherId")
    List<CourseTeacher> findByTeacherId(@Param("teacherId") int teacherId);
}
