package com.global.school.controller;

import com.global.school.entity.Teacher;
import com.global.school.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService){
        this.teacherService=teacherService;
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(Teacher teacher){
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(teacher));
    }
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers(){
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getByTeacherId(@PathVariable Long id){
        return ResponseEntity.ok(teacherService.getByTeacherId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @Validated @RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, teacher));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long  id){
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
