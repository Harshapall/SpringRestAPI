package com.rest.cruds;

import com.rest.cruds.StudentRepo.StudentRepo;
import com.rest.cruds.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CrudsApplicationTests {

	@Autowired
	private StudentRepo studentRepo;

	@Test
	void checkRepo() {
		Assertions.assertNotNull(studentRepo);
	}


	@Test
	void addDataTest() {
		Student student = new Student(12,"pranav","pranav@gmail.com","Madhurai");
		student = studentRepo.save(student);
		Assertions.assertNotNull(student);
		Assertions.assertEquals("pranav", student.getStudentName());
		Assertions.assertEquals("pranav@gmail.com", student.getStudentEmail());
		Assertions.assertEquals("Madhurai", student.getStudentAddress());
	}

	@Test
	void compareById(){
		Student stud =  studentRepo.getById(652);
		System.out.println(stud);
		Assertions.assertEquals(stud.getStudentName(),"pranav");
	}
	@Test
	void deleteDataById() {
		Student student = new Student();
		studentRepo.deleteById(student.getId());
		Assertions.assertFalse(studentRepo.existsById(student.getId()));
	}

	@Test
	void getAllDetails() {
		List<Student> students = studentRepo.findAll();
		for(Student s : students)
			System.out.println(s);
		Assertions.assertFalse(students.isEmpty());
	}

	@Test
	void getStudentByid(){
		Student s1=  studentRepo.getById(202);
		System.out.println(s1);
		Assertions.assertNotNull(s1);
	}


}
