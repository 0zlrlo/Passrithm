package com.example.passrithm.controller.algorithmmaker;

public class Post {
    String key;
    PostSelectedBox postSelectedBox;

    public Post(String key, PostSelectedBox postSelectedBox) {
        this.key = key;
        this.postSelectedBox = postSelectedBox;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return key + "\n";
    }
}
