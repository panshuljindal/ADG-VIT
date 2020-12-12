package com.example.adgexternals;

public class questionObject {
    private String _id;
    private String description;
    private String image;

    public questionObject(String _id, String description, String image) {
        this._id = _id;
        this.description = description;
        this.image = image;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
