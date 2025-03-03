package com.chaduvuko.V1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VideoID")
    private int videoId;

    @ManyToOne
    @JoinColumn(name = "LectureID", nullable = false)
    private Lecture lecture;

    @Column(name = "VideoURL", nullable = false, length = 255)
    private String videoUrl;

    @Column(name = "Duration")
    private int duration;

    @Column(name = "DRMProtected")
    private boolean drmProtected;

    // Getters and Setters

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isDrmProtected() {
        return drmProtected;
    }

    public void setDrmProtected(boolean drmProtected) {
        this.drmProtected = drmProtected;
    }
}

