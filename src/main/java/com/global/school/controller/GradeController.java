package com.global.school.controller;

import com.global.school.entity.Grade;
import com.global.school.service.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/grades")
public class GradeController {
    private final GradeService gradeService;

    public GradeController(GradeService gradeService){
        this.gradeService=gradeService;
    }

    @PostMapping
    public ResponseEntity<Grade> createGrade(@Validated @RequestBody Grade grade){
        return ResponseEntity.status(HttpStatus.CREATED).body(gradeService.createGrade(grade));
    }
    @GetMapping
    public ResponseEntity<List<Grade>> getAllGrades(){
        return ResponseEntity.ok(gradeService.getAllGrades());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Grade> getByGradeId(@PathVariable Long id){
        return ResponseEntity.ok(gradeService.getByGradeId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @Validated @RequestBody Grade grade) {
        return ResponseEntity.ok(gradeService.updateGrade(id, grade));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long  id){
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
}
