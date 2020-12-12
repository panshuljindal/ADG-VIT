package com.example.adgexternals;

public class recyler2item {
    private String heading;
    private String venue;
    private String time;
    private String day;
    private String month;

    public recyler2item(String heading, String venue, String time, String day, String month) {
        this.heading = heading;
        this.venue = venue;
        this.time = time;
        this.day = day;
        this.month = month;
    }

    public String getHeading() {
        return heading;
    }

    public String getVenue() {
        return venue;
    }

    public String getTime() {
        return time;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }
}
