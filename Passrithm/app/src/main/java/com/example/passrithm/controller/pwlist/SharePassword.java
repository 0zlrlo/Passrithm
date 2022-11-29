package com.example.passrithm.controller.pwlist;

public class SharePassword {
    private String password;
    private String email;
    private String domain;

    public SharePassword(){}

    public SharePassword(String password, String domain,String email){
        this.password = password;
        this.email=email;
        this.domain = domain;
    }

    public String getEmail(){return email;}
    public void setEmail(String emailId){this.email=emailId;}

    public String getPassword(){return password;}
    public void setPassword(String passwordId){this.password=passwordId;}

    public String getDomain(){return domain;}
    public void setDomain(String domain){this.domain=domain;}

    @Override
    public String toString() {
        return password + " " + email + " " + domain;
    }
}
