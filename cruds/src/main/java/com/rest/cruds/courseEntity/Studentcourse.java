package com.rest.cruds.courseEntity;

import com.rest.cruds.entity.Student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Studentcourse {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false) 
    private long cid;
    
    @Column(name="course_name")
    private String courseName;
    
    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
