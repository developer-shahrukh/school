package com.global.school.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="Students")
public class Student {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer studentId;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private  String address;

    private String image;
    @Column(nullable = false)

    private String bloodType;
    @Column(nullable = false)
    private String gender;


    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private String birthday;

    @JoinColumn(name="parent_id")
    private Parent parent;

    @JoinColumn(name="grade_id")
    private Grade grade;

    @ManyToOne
    @JoinColumn(name="class_id")
    private SchoolClass aClass;
}
