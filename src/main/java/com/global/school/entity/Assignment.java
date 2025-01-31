package com.global.school.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="Assignments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assignmentId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date DueDate;

    @JoinColumn(name="lesson_id")
    private Lesson lesson;
}
