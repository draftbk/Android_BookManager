package com.example.slf.bookmanager.Bean;

/**
 * Created by slf on 16/4/5.
 */
public class Book {
    private String name;
    private String time;
    private String price;
    private String type;
    private String concise;
    private int tag;

    public Book(String name, String time, String price, String type, String concise,int tag) {
        this.name = name;
        this.time = time;
        this.price = price;
        this.type = type;
        this.concise = concise;
        this.tag=tag;

    }

    public String getName() {
        return name;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConcise() {
        return concise;
    }

    public void setConcise(String concise) {
        this.concise = concise;
    }
}
