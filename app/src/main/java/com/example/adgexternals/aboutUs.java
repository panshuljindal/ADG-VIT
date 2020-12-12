package com.example.adgexternals;

public class aboutUs {

    private String name;
    private String github;
    private String linkedin;
    private String email;

    public aboutUs(String name, String github, String linkedin, String email) {
        this.name = name;
        this.github = github;
        this.linkedin = linkedin;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getGithub() {
        return github;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getEmail() {
        return email;
    }
}
