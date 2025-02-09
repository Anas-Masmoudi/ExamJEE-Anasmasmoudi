package com.example.springinitializr.dao.repositories;

import com.example.springinitializr.dao.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
