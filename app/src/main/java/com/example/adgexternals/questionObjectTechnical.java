package com.example.adgexternals;

public class questionObjectTechnical {

    String _id;
    String questionDescription;
    questionsOptions options;
    String yearofstudy;
    String __v;

    public questionObjectTechnical(String _id, String questionDescription, questionsOptions options, String yearofstudy, String __v) {
        this._id = _id;
        this.questionDescription = questionDescription;
        this.options = options;
        this.yearofstudy = yearofstudy;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public questionsOptions getOptions() {
        return options;
    }

    public void setOptions(questionsOptions options) {
        this.options = options;
    }

    public String getYearofstudy() {
        return yearofstudy;
    }

    public void setYearofstudy(String yearofstudy) {
        this.yearofstudy = yearofstudy;
    }

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }
}
