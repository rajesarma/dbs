package com.dbs.customerservice.model;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {

    private static final long serialVersionUID = -7788619177798333712L;

    private long id;
    private String name;
    private int age;
    private String pan;
//    private long salary;

    public Customer() {

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", pan='" + pan + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getId() == customer.getId() &&
                getAge() == customer.getAge() &&
                Objects.equals(getName(), customer.getName()) &&
                Objects.equals(getPan(), customer.getPan());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getPan());
    }
}

