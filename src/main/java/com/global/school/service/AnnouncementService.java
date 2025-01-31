package com.global.school.service;

import com.global.school.entity.Announcement;
import com.global.school.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService {
    private AnnouncementRepository announcementRepository;

    public Announcement createAnnouncement(Announcement announcement){
        return announcementRepository.save(announcement);
    }

    public Announcement getByAnnouncementId(Long announcementId){
        return announcementRepository.findById(announcementId).orElseThrow(()-> new RuntimeException("Announcement not found."));
    }

    public List<Announcement> getAllAnnouncements(){
        return announcementRepository.findAll();
    }

    public void deleteAnnouncement(Long announcementId){
        announcementRepository.deleteById(announcementId);
    }

    public Announcement updateAnnouncement(Long announcementId,Announcement announcement){
        Announcement existingAnnouncement=getByAnnouncementId(announcementId);
        existingAnnouncement.setTitle(announcement.getTitle());
        existingAnnouncement.setDescription(announcement.getDescription());
        existingAnnouncement.setDate(announcement.getDate());
        existingAnnouncement.setAClass(announcement.getAClass());
        return announcementRepository.save(existingAnnouncement);
    }
}
