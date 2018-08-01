package com.thoughtworks.jpa.mall.model;

import javax.persistence.*;

@Entity
@Table(name = "commodities")
public class Commodity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String name;
    private double price;
    private String type;
    private String band;
    private String description;
    private String date;
    private String location;

    public Commodity(){

    }

    public Commodity(String name, double price, String type, String band) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.band = band;
    }

    public Commodity(String name, double price, String type, String band, String description, String date, String location) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.band = band;
        this.description = description;
        this.date = date;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
