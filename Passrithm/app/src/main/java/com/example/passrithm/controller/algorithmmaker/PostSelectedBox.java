package com.example.passrithm.controller.algorithmmaker;

import java.util.List;

public class PostSelectedBox {
    String name;
    List<SelectedBox> selectedBoxes;

    PostSelectedBox() {}

    PostSelectedBox(String name, List<SelectedBox> selectedBoxes) {
        this.name = name;
        this.selectedBoxes = selectedBoxes;
    }

    public String getName() {
        return name;
    }

    public List<SelectedBox> getSelectedBoxes() {
        return selectedBoxes;
    }

    @Override
    public String toString(){
        return name + "\n";
    }
}
