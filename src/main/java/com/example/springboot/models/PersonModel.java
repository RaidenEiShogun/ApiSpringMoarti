package com.example.springboot.models;

public class PersonModel implements BaseModel {
    private String name;
    private int age;

    public PersonModel() {
        this("", 0);
    }

    public PersonModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Implement any additional methods or overrides as needed
}

