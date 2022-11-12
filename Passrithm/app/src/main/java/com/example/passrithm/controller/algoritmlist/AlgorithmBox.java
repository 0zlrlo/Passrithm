package com.example.passrithm.controller.algoritmlist;

public class AlgorithmBox {
//    Integer id = 0;
//    val name : String,
//    val explanation : String,
//    val example : String,
//    val isPopup : Boolean,
//    val explain : String,
    int id;
    String name;
    String explanation;
    String example;
    Boolean isPopUp;
    String explain;

    public AlgorithmBox(int id, String name, String explanation, String example, boolean isPopUp, String explain) {
        this.id = id;
        this.name = name;
        this.explanation = explanation;
        this.example = example;
        this.isPopUp = isPopUp;
        this.explain = explain;
    }
}
