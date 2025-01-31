package com.global.school.controller;

import com.global.school.entity.Exam;
import com.global.school.service.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exams")
public class ExamController {
    private final ExamService examService;

    public ExamController(ExamService examService){
        this.examService=examService;
    }

    @PostMapping
    public ResponseEntity<Exam> createExam(@Validated @RequestBody Exam exam){
        return ResponseEntity.status(HttpStatus.CREATED).body(examService.createExam(exam));
    }
    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams(){
        return ResponseEntity.ok(examService.getAllExams());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Exam> getByExamId(@PathVariable Long id){
        return ResponseEntity.ok(examService.getByExamId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable Long id, @Validated @RequestBody Exam exam) {
        return ResponseEntity.ok(examService.updateExam(id, exam));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long  id){
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }
}
