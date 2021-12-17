package com.example.testanytehnology.strim.model;

import java.util.TreeSet;

public class TestComparator {
    public static void main(String[] args) {
        PersonComparator pcomp = new PersonComparator();
        TreeSet<User> people = new TreeSet<User>(pcomp);
        people.add(new User(1, "Tom", 12, 10, "man"));
        people.add(new User(2, "Nick", 15, 20, "man"));
        people.add(new User(3, "Alice", 18, 15, "woman"));
        people.add(new User(4, "Bill", 11, 14, "man"));
        for(User  p : people){
            System.out.println(p.getName());
        }
    }
}
