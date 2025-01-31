package com.global.school.service;

import com.global.school.entity.Grade;
import com.global.school.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    private GradeRepository gradeRepository;

    public Grade createGrade(Grade grade){
        return gradeRepository.save(grade);
    }
    public Grade getByGradeId(Long gradeId){
        return gradeRepository.findById(gradeId).orElseThrow(()->new RuntimeException("Grade not found."));
    }
    public List<Grade> getAllGrades(){
        return gradeRepository.findAll();
    }
    public void deleteGrade(Long gradeId){
        gradeRepository.deleteById(gradeId);
    }
    public Grade updateGrade(Long gradeId, Grade grade){
        Grade existingGrade=getByGradeId(gradeId);
        existingGrade.setLevel(grade.getLevel());
        return gradeRepository.save(existingGrade);
    }
}
