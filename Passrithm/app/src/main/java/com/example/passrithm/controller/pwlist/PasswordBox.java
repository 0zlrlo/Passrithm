package com.example.passrithm.controller.pwlist;

import com.example.passrithm.controller.algorithmmaker.PostPassword;

public class PasswordBox {
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

}
