package com.global.school.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="attendances")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attendanceId;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Boolean present;

    @JoinColumn(name="student_id")
    private Student student;

    @JoinColumn(name="lesson_id")
    private Lesson lesson;


}
