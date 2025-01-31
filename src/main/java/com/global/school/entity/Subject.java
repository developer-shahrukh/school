package com.global.school.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subjectId;

    @Column(nullable = false,unique = true)
    private String name;
}
