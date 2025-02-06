package com.global.school.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="classes")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Integer classId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer capacity;

    @JoinColumn(name="teacher_id")
    private Teacher teacher;

    @JoinColumn(name="grade_id")
    private Grade grade;

}
