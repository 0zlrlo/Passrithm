package com.example.passrithm.controller.pwlist;

public class PasswordBox {
    String domain;
    String password;

    public PasswordBox() {}

    public PasswordBox(String siteDomain, String password) {
        this.domain = siteDomain;
        this.password = password;
    }

    public String getDomain() {
        return domain;
    }

    public String getPassword() {
        return password;
    }
}
