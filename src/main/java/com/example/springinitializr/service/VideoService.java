package com.example.springinitializr.service;

import com.example.springinitializr.dao.entities.Video;

import java.util.List;

public interface VideoService {

    Video saveVideo(Video video);

    List<Video> fetchVideoList();

    Video updateVideo(Video video);

    boolean deleteVideoById(Long videoId);
}
