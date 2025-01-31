package com.global.school.controller;

import com.global.school.entity.Result;
import com.global.school.service.ResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/results")
public class ResultController {
    private final ResultService resultService;

    public ResultController(ResultService resultService){
        this.resultService=resultService;
    }

    @PostMapping
    public ResponseEntity<Result> createResult(@Validated @RequestBody Result result){
        return ResponseEntity.status(HttpStatus.CREATED).body(resultService.createResult(result));
    }
    @GetMapping
    public ResponseEntity<List<Result>> getAllResults(){
        return ResponseEntity.ok(resultService.getAllResults());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Result> getByResultId(@PathVariable Long id){
        return ResponseEntity.ok(resultService.getByResultId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable Long id, @Validated @RequestBody Result result) {
        return ResponseEntity.ok(resultService.updateResult(id, result));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long  id){
        resultService.deleteResult(id);
        return ResponseEntity.noContent().build();
    }
}
