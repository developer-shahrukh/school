package com.global.school.controller;

import com.global.school.entity.Admin;
import com.global.school.entity.Subject;
import com.global.school.service.AdminService;
import com.global.school.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Subjects")
public class SubjectController {
    private final SubjectService subjectService;
    public SubjectController(SubjectService subjectService){
        this.subjectService=subjectService;
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@Validated @RequestBody Subject subject){
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.createSubject(subject));
    }
    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects(){
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Subject> getBySubjectId(@PathVariable Long id){
        return ResponseEntity.ok(subjectService.getBySubjectId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Long id, @Validated @RequestBody Subject subject) {
        return ResponseEntity.ok(subjectService.updateSubject(id, subject));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long  id){
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }
}
