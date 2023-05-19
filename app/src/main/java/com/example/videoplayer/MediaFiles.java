package com.example.videoplayer;

import java.security.PrivateKey;

public class MediaFiles {

    private String id;
    private String title;
    private String displayname;
    private String size;
    private String duration;
    private String path;
    private String dateadded;

    public MediaFiles(String id, String title, String displayname, String size, String duration, String path, String dateadded) {
        this.id = id;
        this.title = title;
        this.displayname = displayname;
        this.size = size;
        this.duration = duration;
        this.path = path;
        this.dateadded = dateadded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDateadded() {
        return dateadded;
    }

    public void setDateadded(String dateadded) {
        this.dateadded = dateadded;
    }
}
