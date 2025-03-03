package com.chaduvuko.V1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Lectures")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LectureID")
    private int lectureId;

    @ManyToOne
    @JoinColumn(name = "ModuleID", nullable = false)
    private Module module;

    @Column(name = "Title", nullable = false, length = 200)
    private String title;

    @Column(name = "DownloadableMaterialsURL", length = 255)
    private String downloadableMaterialsUrl;

    @Column(name = "LectureOrder", nullable = false) // Renamed from "Order" to "LectureOrder"
    private int lectureOrder;

    // Getters and Setters

    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDownloadableMaterialsUrl() {
        return downloadableMaterialsUrl;
    }

    public void setDownloadableMaterialsUrl(String downloadableMaterialsUrl) {
        this.downloadableMaterialsUrl = downloadableMaterialsUrl;
    }

    public int getLectureOrder() {
        return lectureOrder;
    }

    public void setLectureOrder(int lectureOrder) {
        this.lectureOrder = lectureOrder;
    }
}

