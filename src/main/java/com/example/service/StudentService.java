package com.example.service;

import com.example.entity.Student;

import java.util.List;

public interface StudentService {

    public String createStudent(Student student);
    public Student getSingleStudent(Long id);
    public List<Student> getAllStudent();


}
