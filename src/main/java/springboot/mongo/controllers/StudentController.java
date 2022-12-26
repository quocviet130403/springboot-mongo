package springboot.mongo.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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

    @GetMapping("/{lastName}")
    ResponseObj getStudentByName(@PathVariable String lastName) {
        return studentService.getStudentByName(lastName);
    }

    @PostMapping
    ResponseObj insertStudent(@RequestBody @Valid Student newStudent) {
        return studentService.addStudent(newStudent);
    }

    @PutMapping("/update/{studentId}")
    ResponseObj updateStudent(@RequestBody @Valid Student newStudent, @PathVariable String studentId) {
        return studentService.updateStudent(newStudent, studentId);
    }

    @DeleteMapping("/delete/{studentId}")
    ResponseObj deleteStudent(@PathVariable String studentId) {
        return studentService.deleteStudent(studentId);
    }
}
