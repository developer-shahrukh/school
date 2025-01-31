package com.global.school.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gradeId;

    @Column(nullable = false,unique = true)
    private Integer level;
}
