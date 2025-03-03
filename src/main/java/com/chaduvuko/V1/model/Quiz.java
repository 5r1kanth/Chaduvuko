package com.chaduvuko.V1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuizID")
    private int quizId;

    @ManyToOne
    @JoinColumn(name = "CourseID", nullable = false)
    private Course course;

    @Column(name = "Title", nullable = false, length = 200)
    private String title;

    @Column(name = "Description", length = 255)
    private String description;

    @Column(name = "TotalPoints")
    private int totalPoints;

    // Getters and Setters

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
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

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}

