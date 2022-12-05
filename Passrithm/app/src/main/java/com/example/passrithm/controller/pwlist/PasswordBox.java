package com.example.passrithm.controller.pwlist;

import com.example.passrithm.controller.algorithmmaker.PostPassword;

import java.io.Serializable;

public class PasswordBox implements Serializable {
    String domain;
    String password;



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

    @Override
    public String toString() {
        return domain + " : " + password + "\n";
    }
}
