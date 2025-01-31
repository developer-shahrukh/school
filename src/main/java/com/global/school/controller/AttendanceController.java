package com.global.school.controller;


import com.global.school.entity.Attendance;
import com.global.school.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService){
        this.attendanceService=attendanceService;
    }

    @PostMapping
    public ResponseEntity<Attendance> createAttendance(@Validated @RequestBody Attendance attendance){
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.createAttendance(attendance));
    }
    @GetMapping
    public ResponseEntity<List<Attendance>> getAllAttendances(){
        return ResponseEntity.ok(attendanceService.getAllAttendances());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getByAttendanceId(@PathVariable Long id){
        return ResponseEntity.ok(attendanceService.getByAttendanceId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable Long id, @Validated @RequestBody Attendance attendance) {
        return ResponseEntity.ok(attendanceService.updateAttendance(id, attendance));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long  id){
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}
