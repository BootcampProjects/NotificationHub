package org.kodluyoruz.trendyol.model;

import java.util.Date;
import java.util.Random;

public class Company {
    private int id;
    private String name;
    private Date createdDate;

    public Company(String name) {
        this.id = new Random().nextInt(99999);
        this.name = name;
        this.createdDate = new Date();
        System.out.println(name + " company id : " + id);
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

    public Date getCreatedDate() {
        return createdDate;
    }
}
