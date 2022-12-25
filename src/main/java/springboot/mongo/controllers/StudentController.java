package springboot.mongo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.mongo.collections.ResponseObj;
import springboot.mongo.collections.Student;
import springboot.mongo.services.StudentService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @GetMapping
    ResponseObj getAllStudents() {
        return studentService.getAllStudent();
    }

    @PostMapping
    ResponseObj insertStudent(@RequestBody Student newStudent) {
        return studentService.addStudent(newStudent);
    }
}
