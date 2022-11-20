package com.example.passrithm.controller.algorithmmaker;

public class PostSelectedBox {
    String name;
    private int viewType;

    PostSelectedBox(String name, int viewType) {
        this.name = name;
        this.viewType = viewType;
    }

    public String getName() {
        return name;
    }

    public int getViewType() {
        return viewType;
    }
}
