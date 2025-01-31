package com.global.school.controller;

import com.global.school.entity.Parent;
import com.global.school.service.ParentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parents")
public class ParentController {
    private final ParentService parentService;

    public ParentController(ParentService parentService){
        this.parentService=parentService;
    }

    @PostMapping
    public ResponseEntity<Parent> createParent(@Validated @RequestBody Parent parent){
        return ResponseEntity.status(HttpStatus.CREATED).body(parentService.createParent(parent));
    }
    @GetMapping
    public ResponseEntity<List<Parent>> getAllParents(){
        return ResponseEntity.ok(parentService.getAllParents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Parent> getByParentId(@PathVariable Long id){
        return ResponseEntity.ok(parentService.getByParentId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Parent> updateParent(@PathVariable Long id, @Validated @RequestBody Parent parent) {
        return ResponseEntity.ok(parentService.updateParent(id, parent));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable Long  id){
        parentService.deleteParent(id);
        return ResponseEntity.noContent().build();
    }
}
