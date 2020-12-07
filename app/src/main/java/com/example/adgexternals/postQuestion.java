package com.example.adgexternals;

public class postQuestion {
    String qid;
    String response;

    public postQuestion(String qid, String response) {
        this.qid = qid;
        this.response = response;
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
