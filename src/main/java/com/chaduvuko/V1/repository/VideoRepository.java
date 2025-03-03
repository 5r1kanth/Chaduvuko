package com.chaduvuko.V1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chaduvuko.V1.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {

}
