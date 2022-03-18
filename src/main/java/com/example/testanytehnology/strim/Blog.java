package com.example.testanytehnology.strim;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

class Blog {
    private String authorName;
    private List<String> comments;

    public Blog() {
    }

    public Blog(String authorName, List<String> comments) {
        this.authorName = authorName;
        this.comments = comments;
    }

    public String getAuthorName() {
        return authorName;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "authorName='" + authorName + '\'' +
                ", comments=" + comments +
                '}';
    }
}
