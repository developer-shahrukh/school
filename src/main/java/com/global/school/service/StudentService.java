package com.global.school.service;

import com.global.school.entity.Student;
import com.global.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public Student createStudent(Student student){return studentRepository.save(student);}

    public Student getByStudentId(Long studentId){
        return studentRepository.findById(studentId).orElseThrow(()-> new RuntimeException("Student not found."));
    }

    public List<Student> getAllStudents(){return studentRepository.findAll();}

    public void deleteStudent(Long studentId){studentRepository.deleteById(studentId);}

    public Student updateStudent(Long studentId, Student student){
        Student existingStudent=getByStudentId(studentId);
        existingStudent.setUsername(student.getUsername());
        existingStudent.setName(student.getName());
        existingStudent.setSurname(student.getSurname());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPhoneNumber(student.getPhoneNumber());
        existingStudent.setAddress(student.getAddress());
        existingStudent.setImage(student.getImage());
        existingStudent.setBloodType(student.getBloodType());
        existingStudent.setGender(student.getGender());
        existingStudent.setBirthday(student.getBirthday());
        existingStudent.setParent(student.getParent());
        existingStudent.setGrade(student.getGrade());
        existingStudent.setAClass(student.getAClass());
        return studentRepository.save(existingStudent);
    }
}

