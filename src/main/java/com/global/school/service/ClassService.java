package com.global.school.service;

import com.global.school.entity.SchoolClass;
import com.global.school.repository.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
    private ClassRepository classRepository;

    public SchoolClass createClass(SchoolClass aClass){
        return classRepository.save(aClass);
    }

    public SchoolClass getByClassId(Long classId){
        return classRepository.findById(classId).orElseThrow(()-> new RuntimeException("Class not found."));
    }
    public List<SchoolClass> getAllClasses(){
        return classRepository.findAll();
    }
    public void deleteClass(Long classId){
        classRepository.deleteById(classId);
    }
    public SchoolClass updateClass(Long classId, SchoolClass aClass){
        SchoolClass existingClass=getByClassId(classId);
        existingClass.setName(aClass.getName());
        existingClass.setCapacity(aClass.getCapacity());
        existingClass.setTeacher(aClass.getTeacher());
        existingClass.setGrade(aClass.getGrade());
        return classRepository.save(existingClass);
    }
}
