package com.gupao.springkafkademo.entity;

import javax.persistence.*;
//
@Entity(name = "Company")//名字
@Table(name = "Company")//对应数据库表中名字
public class Company {

    @Id//数据库中主键标识
    @GeneratedValue//自增标识
    private int id;

    @Column(name = "name")//name属性为表的字段别名
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "address")
    private String address;

    @Column(name = "salary")
    private float  salary;

    public Company() {
    }

    public Company(int id, String name, int age, String address, float salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
