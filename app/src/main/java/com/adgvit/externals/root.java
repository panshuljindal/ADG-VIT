package com.adgvit.externals;

public class root {
    userDetails userDetails;

    public root(com.adgvit.externals.userDetails userDetails) {
        this.userDetails = userDetails;
    }

    public com.adgvit.externals.userDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(com.adgvit.externals.userDetails userDetails) {
        this.userDetails = userDetails;
    }
}
