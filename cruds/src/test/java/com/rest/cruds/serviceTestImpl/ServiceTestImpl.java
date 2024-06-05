package com.rest.cruds.serviceTestImpl;

import com.rest.cruds.StudentRepo.StudentRepo;
import com.rest.cruds.entity.Student;
import com.rest.cruds.service.Studentservice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ServiceTestImpl {

    @Autowired
    private Studentservice studentservice;

    @MockBean
    private StudentRepo studentRepo;

    @BeforeEach
    void setup(){
        Optional<Student> student = Optional.of(new Student(2, "Balu", "balu@gmail.com", "Nellore"));
       Mockito.when(studentRepo.findById(2L)).thenReturn(student);
    }

    @Test
    public void testGetStudentById() {
        Student studer = studentservice.findStudentById(2);
        System.out.print("exec"+studer);
        System.out.print(studer.getStudentName());
        assertEquals("Balu", studer.getStudentName());
    }
}
