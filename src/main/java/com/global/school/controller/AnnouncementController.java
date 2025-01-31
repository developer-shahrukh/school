package com.global.school.controller;


import com.global.school.entity.Announcement;
import com.global.school.service.AnnouncementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService){
        this.announcementService=announcementService;
    }

    @PostMapping
    public ResponseEntity<Announcement> createAnnouncement(@Validated @RequestBody Announcement announcement){
        return ResponseEntity.status(HttpStatus.CREATED).body(announcementService.createAnnouncement(announcement));
    }
    @GetMapping
    public ResponseEntity<List<Announcement>> getAllAnnouncements(){
        return ResponseEntity.ok(announcementService.getAllAnnouncements());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Announcement> getByAnnouncementId(@PathVariable Long id){
        return ResponseEntity.ok(announcementService.getByAnnouncementId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Announcement> updateAnnouncement(@PathVariable Long id, @Validated @RequestBody Announcement announcement) {
        return ResponseEntity.ok(announcementService.updateAnnouncement(id, announcement));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable Long  id){
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.noContent().build();
    }
}
