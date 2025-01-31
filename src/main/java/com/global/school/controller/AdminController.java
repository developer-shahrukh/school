package com.global.school.controller;


import com.global.school.entity.Admin;
import com.global.school.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService=adminService;
    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@Validated @RequestBody Admin admin){
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.createAdmin(admin));
    }
    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins(){
        return ResponseEntity.ok(adminService.getAllAdmins());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getByAdminId(@PathVariable Long id){
        return ResponseEntity.ok(adminService.getByAdminId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @Validated @RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.updateAdmin(id, admin));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long  id){
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
