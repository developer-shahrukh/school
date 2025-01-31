package com.global.school.service;

import com.global.school.entity.Admin;
import com.global.school.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private AdminRepository adminRepository;

    public Admin createAdmin(Admin admin) {return adminRepository.save(admin);}

    public Admin getByAdminId(Long adminId) {
        return adminRepository.findById(adminId).orElseThrow(()-> new RuntimeException("Admin not found"));
    }

    public List<Admin> getAllAdmins(){
        return adminRepository.findAll();
    }

    public void deleteAdmin(Long adminId){
        adminRepository.deleteById(adminId);
    }

    public Admin updateAdmin(Long adminId, Admin admin){
        Admin existingAdmin=getByAdminId(adminId);
        existingAdmin.setUsername(admin.getUsername());
        return adminRepository.save(existingAdmin);
    }
}
