package com.example.adgexternals;

public class loginResponse {
    String regno;
    String password;
    String Token;
    String message;

    public loginResponse(String regno, String password, String token, String message) {
        this.regno = regno;
        this.password = password;
        Token = token;
        this.message = message;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
