package com.global.school.controller;


import com.global.school.entity.Lesson;
import com.global.school.service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService){
        this.lessonService=lessonService;
    }

    @PostMapping
    public ResponseEntity<Lesson> createLesson(@Validated @RequestBody Lesson lesson){
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.createLesson(lesson));
    }
    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons(){
        return ResponseEntity.ok(lessonService.getAllLessons());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getByLessonId(@PathVariable Long id){
        return ResponseEntity.ok(lessonService.getByLessonId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @Validated @RequestBody Lesson lesson) {
        return ResponseEntity.ok(lessonService.updateLesson(id, lesson));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long  id){
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }


}


