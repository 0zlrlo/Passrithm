package com.example.passrithm.controller.algorithmmaker;

public class Post {
    String key;
    PostSelectedBox postSelectedBox;

    public Post(String key, PostSelectedBox postSelectedBox) {
        this.key = key;
        this.postSelectedBox = postSelectedBox;
    }

    @Override
    public String toString() {
        return key + "\n";
    }
}
