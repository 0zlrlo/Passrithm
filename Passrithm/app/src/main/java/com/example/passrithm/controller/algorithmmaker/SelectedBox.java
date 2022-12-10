package com.example.passrithm.controller.algorithmmaker;

import java.io.Serializable;

public class SelectedBox implements Serializable {
    String name;
    String inputData;
    private int viewType;

    SelectedBox() {}

    SelectedBox(String name, String inputData, int viewType) {
        this.name = name;
        this.inputData = inputData;
        this.viewType = viewType;
    }

    public String getName() {
        return name;
    }

    public String getInputData() {
        return inputData;
    }

    public int getViewType() {
        return viewType;
    }

    @Override
    public String toString() {
        return name;
    }
}
