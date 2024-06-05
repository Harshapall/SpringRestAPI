package com.rest.cruds.serviceTestImpl;

import com.rest.cruds.StudentRepo.StudentRepo;
import com.rest.cruds.controller.Studentcontroller;
import com.rest.cruds.entity.Student;
import com.rest.cruds.service.Studentservice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentcontrollerTestImpl {

    @Mock
    private Studentservice studentservice;

    @Mock
    private StudentRepo studentRepo;


    @InjectMocks
    private Studentcontroller studentcontroller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveStudent() {
        Student student = new Student(1, "Harsha", "harsha@gmail.com", "Vijayawada");
        doNothing().when(studentservice).SaveStudent(student);
        ResponseEntity<Student> response = studentcontroller.saveStudent(student);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(student, response.getBody());
        verify(studentservice, times(1)).SaveStudent(student);
    }

    @Test
    public void testGetAllStudents() {
        List<Student> students = Arrays.asList(
                new Student(1, "Harsha", "harsha@gmail.com", "Vijayawada"),
                new Student(3, "Harsha", "Harsha@gmail.com", "Nellore"),
                new Student(5, "Harsha", "Harsha@gmail.com", "Nellore")
        );
        when(studentservice.FindAll()).thenReturn(students);
        ResponseEntity<List<Student>> response = studentcontroller.getStudents();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println(response.getBody());
        assertEquals(students, response.getBody());
        verify(studentservice, times(1)).FindAll();
    }



}
