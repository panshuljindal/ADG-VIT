package com.example.adgexternals;

public class root {
    userDetails userDetails;

    public root(com.example.adgexternals.userDetails userDetails) {
        this.userDetails = userDetails;
    }

    public com.example.adgexternals.userDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(com.example.adgexternals.userDetails userDetails) {
        this.userDetails = userDetails;
    }
}
