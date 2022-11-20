package com.example.passrithm.controller.pwlist;

public class PasswordBox {
    String domain;
    String password;
    int id;
    Boolean isPopUp;
    public PasswordBox(String domain, String password,int id,boolean isPopUp){
        this.domain=domain;
        this.password=password;
        this.isPopUp=isPopUp;
        this.id=id;
    }
}
