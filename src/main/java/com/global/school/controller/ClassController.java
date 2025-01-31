package com.global.school.controller;

import com.global.school.entity.SchoolClass;
import com.global.school.service.ClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
public class ClassController {
    private final ClassService classService;

    public ClassController(ClassService classService){
        this.classService=classService;
    }

    @PostMapping
    public ResponseEntity<SchoolClass> createClass(@Validated @RequestBody SchoolClass aClass){
        return ResponseEntity.status(HttpStatus.CREATED).body(classService.createClass(aClass));
    }
    @GetMapping
    public ResponseEntity<List<SchoolClass>> getAllClasses(){
        return ResponseEntity.ok(classService.getAllClasses());
    }
    @GetMapping("/{id}")
    public ResponseEntity<SchoolClass> getByClassId(@PathVariable Long id){
        return ResponseEntity.ok(classService.getByClassId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<SchoolClass> updateClass(@PathVariable Long id, @Validated @RequestBody SchoolClass aClass) {
        return ResponseEntity.ok(classService.updateClass(id, aClass));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long  id){
        classService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }
}
