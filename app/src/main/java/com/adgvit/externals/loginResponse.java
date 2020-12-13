package com.adgvit.externals;

public class loginResponse {
    private String regno;
    private  String password;
    private  String Token;
    private String message;

    public loginResponse(String regno, String password, String Token, String message) {
        this.regno = regno;
        this.password = password;
        this.Token = Token;
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

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
