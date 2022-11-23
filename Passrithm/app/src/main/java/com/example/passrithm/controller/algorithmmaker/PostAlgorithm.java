package com.example.passrithm.controller.algorithmmaker;

public class PostAlgorithm {
    String key;
    PostSelectedBox postSelectedBox;

    public PostAlgorithm(String key, PostSelectedBox postSelectedBox) {
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
