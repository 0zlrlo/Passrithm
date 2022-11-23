package com.example.passrithm.controller.algorithmmaker;

public class PostPassword {
    String domain;
    String password;

    public PostPassword() {}

    public PostPassword(String siteDomain, String password) {
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
