package com.global.school.service;

import com.global.school.entity.Exam;
import com.global.school.repository.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    private ExamRepository examRepository;

    public Exam createExam(Exam exam){
        return examRepository.save(exam);
    }
    public Exam getByExamId(Long examId){
        return examRepository.findById(examId).orElseThrow(()->new RuntimeException("Exam not found."));
    }
    public List<Exam> getAllExams(){
        return examRepository.findAll();
    }
    public void deleteExam(Long examId){
        examRepository.deleteById(examId);
    }
    public Exam updateExam(Long examId, Exam exam){
        Exam existingExam=getByExamId(examId);
        existingExam.setTitle(exam.getTitle());
        existingExam.setStartTime(exam.getStartTime());
        existingExam.setEndTime(exam.getEndTime());
        existingExam.setLesson(exam.getLesson());
        return examRepository.save(existingExam);
    }

}
