package com.example.adgexternals;

public class postQuestion {
    private String qid;
    private String response;
    private String message;

    public postQuestion(String qid, String response) {
        this.qid = qid;
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
