package com.example.agright;

public class TimelineX {
    private String date, title, summary;

    public TimelineX(String date, String summary, String title) {
        this.date = date;
        this.title = title;
        this.summary = summary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
