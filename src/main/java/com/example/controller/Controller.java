package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Student;
import com.example.serviceImpl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class Controller {


    @Autowired
    private StudentServiceImpl studentService;


    @PostMapping("/create")
    public String create(@RequestBody Student student)
    {
        return studentService.createStudent(student);
    }


    @GetMapping("/get-student")
    public Student getStudent(@RequestParam Long id)
    {
        return studentService.getSingleStudent(id);
    }

    @GetMapping("/get-all-students")
    public List<Student> getAllStudents()
    {
        return studentService.getAllStudent();
    }

        @GetMapping("/get-accounts")
        public List<Account> getAccounts()
        {
            return studentService.getAccounts();
        }

}
