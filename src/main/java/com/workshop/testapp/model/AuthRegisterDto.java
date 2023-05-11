package com.workshop.testapp.model;

public class AuthRegisterDto {

    private String name;
    private String username;
    private String password;
    private String phone;
    private String email;

    public AuthRegisterDto() {
    }

    public AuthRegisterDto(String name, String username, String password, String phone, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
