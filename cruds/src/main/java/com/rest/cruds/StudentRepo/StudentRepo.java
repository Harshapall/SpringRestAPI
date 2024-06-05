package com.rest.cruds.StudentRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rest.cruds.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {

	@Query("SELECT DISTINCT s.studentName FROM Student s")
	List<String> findDistinctStudentNames();
	 
	@Query("SELECT s.studentName FROM Student s GROUP BY s.studentName HAVING COUNT(s.studentName) > 1")
	List<String> findDuplicateStudents();
	 
	@Query("SELECT s FROM Student s WHERE s.studentAddress IN ('Hyderabad', 'Paris')")
	List<Student> findCity();

	Student getById(int i);

	Student deleteById(int i);

	Optional<Student> findByStudentName(String studentName);

}
