package com.rest.cruds.CourseRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rest.cruds.courseEntity.Studentcourse;

public interface CourseRepo extends JpaRepository<Studentcourse, Long> {

@Query("SELECT s.id,s.studentName,s.studentEmail,s.studentAddress,c.courseName from Student s inner join Studentcourse c on c.courseName=:coursename")
    List<Object[]> findAllStudentCourses(@Param("coursename") String coursename);
}
