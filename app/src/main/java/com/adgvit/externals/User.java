package com.adgvit.externals;

public class User {
    private String name;
    private String regno;
    private String password;
    private String email;
    private int yearofstudy;
    private String githubLink;
    private String phone;

    public User(String name, String regno, String password, String email, int yearofstudy, String githubLink, String phone) {
        this.name = name;
        this.regno = regno;
        this.password = password;
        this.email = email;
        this.yearofstudy = yearofstudy;
        this.githubLink = githubLink;
        this.phone = phone;
    }

}
