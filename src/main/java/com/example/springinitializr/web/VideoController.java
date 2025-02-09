package com.example.springinitializr.web;

import com.example.springinitializr.dao.entities.Video;
import com.example.springinitializr.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/videos")

public class VideoController {
    @Autowired
    private VideoService videoService;

    // Afficher la liste des vidéos
    @GetMapping
    public String getAllVideos(Model model) {
        model.addAttribute("videos", videoService.fetchVideoList());
        return "index";  // Correspond à index.html
    }

    // Formulaire pour ajouter une nouvelle vidéo
    @GetMapping("/new")
    public String showAddVideoForm(Model model) {
        model.addAttribute("video", new Video());
        return "add-video";  // Correspond à add-video.html
    }

    // Soumettre le formulaire pour ajouter une nouvelle vidéo
    @PostMapping
    public String saveVideo(@ModelAttribute("video") Video video) {
        videoService.saveVideo(video);
        return "redirect:/videos";
    }

    // Formulaire pour modifier une vidéo existante
    @GetMapping("/edit/{id}")
    public String showEditVideoForm(@PathVariable("id") Long videoId, Model model) {
        Video video = videoService.fetchVideoList().stream()
                .filter(v -> v.getVideoId().equals(videoId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Video not found"));
        model.addAttribute("video", video);
        return "edit-video";  // Correspond à edit-video.html
    }

    // Soumettre le formulaire pour modifier la vidéo
    @PostMapping("/edit")
    public String updateVideo(@ModelAttribute("video") Video video) {
        videoService.updateVideo(video);
        return "redirect:/videos";
    }

    // Supprimer une vidéo
    @GetMapping("/delete/{id}")
    public String deleteVideo(@PathVariable("id") Long videoId) {
        videoService.deleteVideoById(videoId);
        return "redirect:/videos";
    }
}



