package com.example.springinitializr.service;

import com.example.springinitializr.dao.entities.Video;
import com.example.springinitializr.dao.repositories.VideoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoManager implements VideoService{

    @PostConstruct
    public void populateData() {
        // Vérifie si la base de données est vide
        if (videoRepository.count() == 0) {
            videoRepository.save(new Video(null, "Harry Potter 4", "https://youtu.be/XO9rqIgzDL0", "Harry potter 4 et la coupe de feu"));
            videoRepository.save(new Video(null, "Seigneur des anneaux 3", "https://youtu.be/RCuDRcK0BBM", "Le Seigneur des anneaux 3 : Le Retour du roi"));
            videoRepository.save(new Video(null, "Hunger games 2", "https://youtu.be/-ZcW_6i2Rkg", "Hunger Games: L'embrasement"));
        }
    }

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public Video saveVideo(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public List<Video> fetchVideoList() {
        return videoRepository.findAll();
    }

    @Override
    public Video updateVideo(Video video) {
        return videoRepository.findById(video.getVideoId())
                .map(existingVideo -> {
                    existingVideo.setVideoName(video.getVideoName());
                    existingVideo.setVideoUrl(video.getVideoUrl());
                    existingVideo.setVideoDescription(video.getVideoDescription());
                    return videoRepository.save(existingVideo);
                })
                .orElseThrow(() -> new RuntimeException("Video not found with id " + video.getVideoId()));
    }

    @Override
    public boolean deleteVideoById(Long videoId) {
        if (videoRepository.existsById(videoId)) {
            videoRepository.deleteById(videoId);
            return true;
        } else {
            return false;
        }
    }
}
