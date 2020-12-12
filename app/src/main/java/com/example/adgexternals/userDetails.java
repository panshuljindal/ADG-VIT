package com.example.adgexternals;

public class userDetails {
    private boolean attemptedTechnical;
    private boolean attemptedManagement;
    private boolean attemptedDesign;
    private int yearofstudy;
    private String id;
    private String name;
    private String regno;
    private String email;
    private String githubLink;
    private String phone;

    public userDetails(boolean attemptedTechnical, boolean attemptedManagement, boolean attemptedDesign, int yearofstudy, String id, String name, String regno, String email, String githubLink, String phone) {
        this.attemptedTechnical = attemptedTechnical;
        this.attemptedManagement = attemptedManagement;
        this.attemptedDesign = attemptedDesign;
        this.yearofstudy = yearofstudy;
        this.id = id;
        this.name = name;
        this.regno = regno;
        this.email = email;
        this.githubLink = githubLink;
        this.phone = phone;
    }

    public boolean getAttemptedTechnical() {
        return attemptedTechnical;
    }

    public void setAttemptedTechnical(boolean attemptedTechnical) {
        this.attemptedTechnical = attemptedTechnical;
    }

    public boolean getAttemptedManagement() {
        return attemptedManagement;
    }

    public void setAttemptedManagement(boolean attemptedManagement) {
        this.attemptedManagement = attemptedManagement;
    }

    public boolean getAttemptedDesign() {
        return attemptedDesign;
    }

    public void setAttemptedDesign(boolean attemptedDesign) {
        this.attemptedDesign = attemptedDesign;
    }

    public int getYearofstudy() {
        return yearofstudy;
    }

    public void setYearofstudy(int yearofstudy) {
        this.yearofstudy = yearofstudy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
