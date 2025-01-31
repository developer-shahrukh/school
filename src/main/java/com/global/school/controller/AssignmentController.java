package com.global.school.controller;


import com.global.school.entity.Assignment;
import com.global.school.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService){
        this.assignmentService=assignmentService;
    }

    @PostMapping
    public ResponseEntity<Assignment> createAssignment(@Validated @RequestBody Assignment assignment){
        return ResponseEntity.status(HttpStatus.CREATED).body(assignmentService.createAssignment(assignment));
    }
    @GetMapping
    public ResponseEntity<List<Assignment>> getAllAssignments(){
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getByAssignmentId(@PathVariable Long id){
        return ResponseEntity.ok(assignmentService.getByAssignmentId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable Long id, @Validated @RequestBody Assignment assignment) {
        return ResponseEntity.ok(assignmentService.updateAssignment(id, assignment));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long  id){
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
