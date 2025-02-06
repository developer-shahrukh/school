package com.global.school.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resultId;

    @Column(nullable = false)
    private Integer score;

    @JoinColumn(name="exam_id")
    private Exam exam;

    @JoinColumn(name="assignment_id")
    private Assignment assignment;

    @JoinColumn(name="student_id")
    private Student student;
}
