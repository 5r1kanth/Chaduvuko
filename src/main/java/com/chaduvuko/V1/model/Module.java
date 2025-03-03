package com.chaduvuko.V1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Modules")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ModuleID")
    private int moduleId;

    @ManyToOne
    @JoinColumn(name = "CourseID", nullable = false)
    private Course course;

    @Column(name = "Title", nullable = false, length = 200)
    private String title;

    @Column(name = "Description", length = 255)
    private String description;

    @Column(name = "ModuleOrder", nullable = false) // Renamed from "Order" to "ModuleOrder"
    private int moduleOrder;

    // Getters and Setters

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getModuleOrder() {
        return moduleOrder;
    }

    public void setModuleOrder(int moduleOrder) {
        this.moduleOrder = moduleOrder;
    }
}
