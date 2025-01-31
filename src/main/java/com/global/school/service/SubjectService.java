package com.global.school.service;

import com.global.school.entity.Subject;
import com.global.school.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private SubjectRepository subjectRepository;

    public Subject createSubject(Subject subject){
        return subjectRepository.save(subject);
    }
    public Subject getBySubjectId(Long subjectId){
        return subjectRepository.findById(subjectId).orElseThrow(()->new RuntimeException("Subject not found."));
    }
    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }
    public void deleteSubject(Long subjectId){
        subjectRepository.deleteById(subjectId);
    }
    public Subject updateSubject(Long subjectId, Subject subject){
        Subject existingSubject=getBySubjectId(subjectId);
        existingSubject.setName(subject.getName());
        return subjectRepository.save(existingSubject);
    }
}
