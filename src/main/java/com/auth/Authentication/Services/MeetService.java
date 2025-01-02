package com.auth.Authentication.Services;

import com.auth.Authentication.entity.Meet;
import com.auth.Authentication.Repository.MeetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeetService {

    private final MeetRepository meetRepository;

    public MeetService(MeetRepository meetRepository) {
        this.meetRepository = meetRepository;
    }

    public Meet createMeet(Meet meet) {
        return meetRepository.save(meet);
    }

    public Meet updateMeet(Long id, Meet updatedMeet) {
        Optional<Meet> existingMeet = meetRepository.findById(id);
        if (existingMeet.isPresent()) {
            Meet meet = existingMeet.get();
            meet.setMeetName(updatedMeet.getMeetName());
            meet.setLocation(updatedMeet.getLocation());
            meet.setDate(updatedMeet.getDate());
            meet.setStartTime(updatedMeet.getStartTime());
            meet.setEndTime(updatedMeet.getEndTime());
            return meetRepository.save(meet);
        } else {
            throw new RuntimeException("Meet with ID " + id + " not found.");
        }
    }

    public void deleteMeet(Long id) {
        meetRepository.deleteById(id);
    }

    public Meet getMeetById(Long id) {
        return meetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meet with ID " + id + " not found."));
    }

    public List<Meet> getAllMeets() {
        return meetRepository.findAll();
    }
}
