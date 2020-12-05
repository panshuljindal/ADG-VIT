package com.example.adgexternals;

public class loginrequest {

        String regno;
        String password;
        String Token;
        String message;
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


