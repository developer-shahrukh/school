package com.global.school.service;

import com.global.school.entity.Parent;
import com.global.school.repository.ParentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentService {
    private ParentRepository parentRepository;

    public Parent createParent(Parent parent){
        return parentRepository.save(parent);
    }
    public Parent getByParentId(Long parentId){
        return parentRepository.findById(parentId).orElseThrow(()->new RuntimeException("Parent not found."));
    }
    public List<Parent> getAllParents(){
        return parentRepository.findAll();
    }
    public void deleteParent(Long parentId){
        parentRepository.deleteById(parentId);
    }
    public Parent updateParent(Long parentId, Parent parent){
        Parent existingParent=getByParentId(parentId);
        existingParent.setUsername(parent.getUsername());
        existingParent.setName(parent.getName());
        existingParent.setSurname(parent.getSurname());
        existingParent.setEmail(parent.getEmail());
        existingParent.setPhoneNumber(parent.getPhoneNumber());
        existingParent.setAddress(parent.getAddress());
        return parentRepository.save(existingParent);
    }
}
