package com.global.school.controller;

import com.global.school.entity.Student;
import com.global.school.service.StudentService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(Student student){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getByStudentId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable long id, @Validated @RequestBody Student student){
        return ResponseEntity.ok(studentService.updateStudent(id,student));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
