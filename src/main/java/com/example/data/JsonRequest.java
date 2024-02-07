package com.example.data;

import java.util.List;

public class JsonRequest {

    private String email;
    private List<String> roles;
    private String birthdate;

    public JsonRequest() {}

    public JsonRequest(String email, List<String> roles, String birthdate) {
        this.email = email;
        this.roles = roles;
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}