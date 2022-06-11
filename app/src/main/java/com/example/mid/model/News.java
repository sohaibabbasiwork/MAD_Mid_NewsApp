package com.example.mid.model;

public class News {
    private String heading,url,description,ref;
    private int id;

    public News(String heading, String url, String description, int id ,String ref) {
        this.heading = heading;
        this.url = url;
        this.description = description;
        this.id = id;
        this.ref=ref;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
