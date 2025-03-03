package com.chaduvuko.V1.model;

public enum QuestionType {
    MULTIPLE_CHOICE("Multiple Choice"),
    TRUE_FALSE("True/False");

    private final String type;

    QuestionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
