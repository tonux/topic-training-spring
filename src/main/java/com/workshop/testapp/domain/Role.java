package com.workshop.testapp.domain;

public enum Role {

    AGENT("AGENT"),
    ADMIN("ADMIN"),
    USER("USER");

    private String authority;

    Role(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

}
