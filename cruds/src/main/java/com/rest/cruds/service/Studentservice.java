package com.rest.cruds.service;

import com.rest.cruds.StudentRepo.StudentRepo;
import com.rest.cruds.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Studentservice {

    @Autowired
    private StudentRepo studentRepo;

    public  void  SaveStudent(Student student){
        studentRepo.save(student);
    }

    public List<Student> FindAll() {
        return studentRepo.findAll();
    }
    public Student findStudentById(long id) {
        Optional<Student> student = studentRepo.findById(id);
        return student.orElse(null);
    }

}
