package com.global.school.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="subject_id")
    private Integer subjectId;

    @Column(nullable = false,unique = true)
    private String name;
}
