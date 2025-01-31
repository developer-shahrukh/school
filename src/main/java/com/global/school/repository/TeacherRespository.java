package com.global.school.repository;

import com.global.school.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRespository extends JpaRepository<Teacher,Long> {}
