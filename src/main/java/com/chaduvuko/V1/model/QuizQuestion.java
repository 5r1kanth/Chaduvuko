package com.chaduvuko.V1.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "QuizQuestions")
public class QuizQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QuestionID")
    private int questionID;

    @ManyToOne
    @JoinColumn(name = "QuizID", referencedColumnName = "QuizID")
    private Quiz quiz; // Assuming a Quiz entity exists

    @Column(name = "QuestionText")
    private String questionText;

    @Enumerated(EnumType.STRING)
    @Column(name = "QuestionType")
    private QuestionType questionType;

    @Column(name = "Options")
    private String options; // You can store the options as a JSON string

    @Column(name = "CorrectAnswer")
    private String correctAnswer;

    // Getters and Setters
    public Integer getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Integer questionID) {
        this.questionID = questionID;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
