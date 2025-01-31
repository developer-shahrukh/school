package com.global.school.service;

import com.global.school.entity.Lesson;
import com.global.school.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    private LessonRepository lessonRepository;

    public Lesson createLesson(Lesson lesson){
        return lessonRepository.save(lesson);
    }
    public Lesson getByLessonId(Long lessonId){
        return lessonRepository.findById(lessonId).orElseThrow(()->new RuntimeException("Lesson not found."));
    }
    public List<Lesson> getAllLessons(){
        return lessonRepository.findAll();
    }
    public void deleteLesson(Long lessonId){
        lessonRepository.deleteById(lessonId);
    }
    public Lesson updateLesson(Long lessonId, Lesson lesson){
        Lesson existingLesson=getByLessonId(lessonId);
        existingLesson.setName(lesson.getName());
        existingLesson.setDay(lesson.getDay());
        existingLesson.setStartTime(lesson.getStartTime());
        existingLesson.setEndTime(lesson.getEndTime());
        existingLesson.setSubject(lesson.getSubject());
        existingLesson.setAClass(lesson.getAClass());
        existingLesson.setTeacher(lesson.getTeacher());
        return lessonRepository.save(existingLesson);
    }

}


