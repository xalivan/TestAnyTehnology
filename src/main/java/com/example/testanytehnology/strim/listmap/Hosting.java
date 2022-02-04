package com.example.testanytehnology.strim.listmap;

import lombok.Value;

@Value
class Hosting {
    int Id;
    String name;
    long websites;

    Hosting(int id, String name, long websites) {
        Id = id;
        this.name = name;
        this.websites = websites;
    }
}
