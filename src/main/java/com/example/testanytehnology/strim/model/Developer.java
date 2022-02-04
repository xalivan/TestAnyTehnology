package com.example.testanytehnology.strim.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

//@Value
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Developer {
    Integer id;
    String name;
    Set<String> book = new HashSet<>();

    public void addBook(String book) {
        this.book.add(book);
    }
}
