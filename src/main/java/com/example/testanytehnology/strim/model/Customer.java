package com.example.testanytehnology.strim.model;

import lombok.Value;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Value
public class Customer {
//    String profilePhotoUrl;
    String name;
    int points;

    public boolean hasOverHundredPoints() {
        return this.points > 100;
    }

//    public boolean hasValidProfilePhoto() throws IOException {
//        URL url = new URL(this.profilePhotoUrl);
//        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//        return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
//    }
}
