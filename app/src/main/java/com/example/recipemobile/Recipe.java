package com.example.recipemobile;

public class Recipe {

    private int Id;

    private String name;

    public Recipe(int id, String name) {
        Id = id;
        this.name = name;
    }

    public Recipe() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
