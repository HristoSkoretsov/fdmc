package org.softuni.fdmc.data.models;

public class Cat {
    private String name;

    private String breed;

    private String color;

    private Integer numberOfLegs;

    private User creator;

    private int views;

    public Cat(String name, String breed, String color, Integer numberOfLegs, User creator) {
        this.setName(name);
        this.setBreed(breed);
        this.setColor(color);
        this.setNumberOfLegs(numberOfLegs);
        this.setCreator(creator);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getNumberOfLegs() {
        return numberOfLegs;
    }

    public void setNumberOfLegs(Integer numberOfLegs) {
        this.numberOfLegs = numberOfLegs;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public int getViews() {
        return this.views;
    }

    synchronized public void increaseViews() {
        this.views++;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + this.name + '\'' +
                ", breed='" + this.breed + '\'' +
                ", color='" + this.color + '\'' +
                ", numberOfLegs=" + this.numberOfLegs +
                ", creator=" + this.creator.getUsername() +
                ", views=" + this.views +
                '}';
    }
}
