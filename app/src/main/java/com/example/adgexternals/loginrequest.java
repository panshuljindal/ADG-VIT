package com.example.adgexternals;

public class loginrequest {

    private String regno;
    private String password;
    private String Token;
    private String message;
    public loginrequest(String regno, String password) {
        this.regno = regno;
        this.password = password;
    }

        public String getToken() {
            return Token;
        }

        public String getMessage() {
            return message;
        }
    }


