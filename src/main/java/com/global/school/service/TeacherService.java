package com.global.school.service;

import com.global.school.entity.Teacher;
import com.global.school.repository.TeacherRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private TeacherRespository teacherRespository;

    public Teacher createTeacher(Teacher teacher){return teacherRespository.save(teacher);}

    public Teacher getByTeacherId(Long teacherId){
        return teacherRespository.findById(teacherId).orElseThrow(()->new RuntimeException("Teacher not found."));
    }

    public List<Teacher> getAllTeachers(){return teacherRespository.findAll();}

    public void deleteTeacher(Long teacherId){
        teacherRespository.deleteById(teacherId);
    }

    public Teacher updateTeacher(Long teacherId,Teacher teacher){
        Teacher existingTeacher=getByTeacherId(teacherId);
        existingTeacher.setUsername(teacher.getUsername());
        existingTeacher.setName(teacher.getName());
        existingTeacher.setSurname(teacher.getSurname());
        existingTeacher.setEmail(teacher.getEmail());
        existingTeacher.setPhoneNumber(teacher.getPhoneNumber());
        existingTeacher.setAddress(teacher.getAddress());
        existingTeacher.setImage(teacher.getImage());
        existingTeacher.setBloodType(teacher.getBloodType());
        existingTeacher.setGender(teacher.getGender());
        existingTeacher.setBirthday(teacher.getBirthday());
        return teacherRespository.save(existingTeacher);
    }

}
