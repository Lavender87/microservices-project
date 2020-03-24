package com.gupao.microservicesproject.graphql.entity;

public class User {

    private int age;
    private long id;
    private String name;
    private Card card;

    public User() {
    }

    public User(int age, long id, String name, Card card) {
        this.age = age;
        this.id = id;
        this.name = name;
        this.card = card;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
