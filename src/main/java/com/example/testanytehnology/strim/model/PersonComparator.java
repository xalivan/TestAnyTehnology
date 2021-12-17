package com.example.testanytehnology.strim.model;

import java.util.Comparator;

public class PersonComparator implements Comparator<User> {
    @Override
    public int compare(User a, User b){
        return a.getAge().compareTo(b.getAge());
    }
}
