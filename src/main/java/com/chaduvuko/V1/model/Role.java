package com.chaduvuko.V1.model;

public enum Role {
	INSTRUCTOR("Instructor"),
    STUDENT("Student");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }

}
