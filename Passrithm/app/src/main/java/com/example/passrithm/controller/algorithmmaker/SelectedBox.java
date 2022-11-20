package com.example.passrithm.controller.algorithmmaker;

public class SelectedBox {
    String name;
    String inputData;
    private int viewType;

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
}
