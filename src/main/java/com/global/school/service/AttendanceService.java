package com.global.school.service;

import com.global.school.entity.Attendance;
import com.global.school.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {
    private AttendanceRepository attendanceRepository;

    public Attendance createAttendance(Attendance attendance){
        return attendanceRepository.save(attendance);
    }

    public Attendance getByAttendanceId(Long attendanceId){
        return attendanceRepository.findById(attendanceId).orElseThrow(()-> new RuntimeException("Attendance not found."));
    }

    public List<Attendance> getAllAttendances(){
        return attendanceRepository.findAll();
    }

    public void deleteAttendance(Long attendanceId){
        attendanceRepository.deleteById(attendanceId);
    }

    public Attendance updateAttendance(Long attendanceId, Attendance attendance){
        Attendance existingAttendance=getByAttendanceId(attendanceId);
        existingAttendance.setDate(attendance.getDate());
        existingAttendance.setPresent(attendance.getPresent());
        existingAttendance.setStudent(attendance.getStudent());
        existingAttendance.setLesson(attendance.getLesson());
        return attendanceRepository.save(existingAttendance);
    }


}
