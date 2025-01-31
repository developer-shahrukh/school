package com.global.school.service;

import com.global.school.entity.Assignment;
import com.global.school.repository.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {
    private AssignmentRepository assignmentRepository;

    public Assignment createAssignment(Assignment assignment){
        return assignmentRepository.save(assignment);
    }

    public Assignment getByAssignmentId(Long assignmentId){
        return assignmentRepository.findById(assignmentId).orElseThrow(()-> new RuntimeException("Assignment not found"));
    }

    public List<Assignment> getAllAssignments(){
        return assignmentRepository.findAll();
    }
    public void deleteAssignment(Long assignmentId){
        assignmentRepository.deleteById(assignmentId);
    }
    public Assignment updateAssignment(Long assignmentId,Assignment assignment){
        Assignment existingAssignment=getByAssignmentId(assignmentId);
        existingAssignment.setTitle(assignment.getTitle());
        existingAssignment.setLesson(assignment.getLesson());
        existingAssignment.setStartDate(assignment.getStartDate());
        existingAssignment.setDueDate(assignment.getDueDate());
        return assignmentRepository.save(existingAssignment);
    }
}
