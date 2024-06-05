package com.rest.cruds.serviceTestImpl;

import com.rest.cruds.StudentRepo.StudentRepo;
import com.rest.cruds.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StudentRepoTestImpl {

    @MockBean
    private StudentRepo studentRepo;

    @BeforeEach
    void setup() {

        Student student1 = new Student(2, "Mahesh", "Mahesh@gmail.com", "Maharashtra");
        Student student2 = new Student(3, "Harsha", "Harsha@gmail.com", "Nellore");
        Student student3 = new Student(5, "Harsha", "Harsha@gmail.com", "Nellore");

        Mockito.when(studentRepo.getById(2L)).thenReturn(student1);
        Mockito.when(studentRepo.findDistinctStudentNames()).thenReturn(List.of("Harsha", "Mahesh"));
    }

    @Test
    public void testGetStudentById() {
        // Calling the repository method and asserting the result
        Student student = studentRepo.getById(2L);
        assertEquals("Mahesh", student.getStudentName());
    }

    @Test
    public void testGetDistinctNames() {
        List<String> distinctNames = studentRepo.findDistinctStudentNames();
        System.out.print(distinctNames);
        List<String> expectedNames = List.of("Harsha", "Mahesh");
        assertArrayEquals(expectedNames.toArray(), distinctNames.toArray());
    }

    @Test
    public void testGetFindCity() {
        List<Student> mockCities = new ArrayList<>();
        mockCities.add(new Student(1, "Harsha", "Harsha@gmail.com", "New York"));
        mockCities.add(new Student(2, "Mahi", "Mahi@gmail.com", "Los Angeles"));
        mockCities.add(new Student(3, "Charlie", "charlie@gmail.com", "Chicago"));

        Mockito.when(studentRepo.findCity()).thenReturn(mockCities);
        List<Student> cities = studentRepo.findCity();
        System.out.print(cities);
        assertEquals(3, cities.size());
        assertEquals("New York", cities.get(0).getStudentAddress());
        assertEquals("Los Angeles", cities.get(1).getStudentAddress());
        assertEquals("Chicago", cities.get(2).getStudentAddress());
    }

}
