package springboot.mongo.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/search/age/{minAge}/{maxAge}")
    ResponseObj getStudentBetweenAge(@PathVariable Integer minAge, @PathVariable Integer maxAge){
        return studentService.getStudentBetweenAge(minAge, maxAge);
    }

    @PostMapping("/filter")
    Page<Student> filterStudent(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) String country,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return studentService.filterStudent(firstName, minAge, maxAge, country, pageable);
    }
}
