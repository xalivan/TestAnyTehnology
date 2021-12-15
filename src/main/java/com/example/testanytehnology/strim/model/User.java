package com.example.testanytehnology.strim.model;


import lombok.*;

import java.util.Comparator;
import java.util.Objects;

@Getter
@Setter
@ToString
public class User implements Comparable<User> {
    Integer id;
    String name;
    Integer age;
    Integer weight;
    String sex;
    @Override
    public int compareTo(User o) {
        return this.getAge().compareTo(o.getAge());
    }
//
//    @Override
//    public int compare(User o1, User o2) {
//        return o1.getAge().compareTo(o2.getAge());
//    }

    public User(Integer id, String name, Integer age, Integer weight, String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.sex = sex;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!name.equals(user.name)) return false;
        if (!age.equals(user.age)) return false;
        if (!weight.equals(user.weight)) return false;
        return sex.equals(user.sex);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + age.hashCode();
        result = 31 * result + weight.hashCode();
        result = 31 * result + sex.hashCode();
        return result;
    }



}
