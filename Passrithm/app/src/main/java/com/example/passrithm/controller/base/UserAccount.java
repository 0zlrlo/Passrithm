package com.example.passrithm.controller.base;

//회원가입 사용자 정보 모델 클래스

public class UserAccount {

    private String Idtoken;  //파이어베이스 아이디(고유 토큰)
    private String emailId;
    private String passwordId;
    private String nameId;
    private String pinId;


    public UserAccount(){ }  //realtime database 사용할 때 빈 생성자

    public UserAccount(String Idtoken, String emailId, String passwordId, String nameId, String pinId){
        this.Idtoken=Idtoken;
        this.emailId=emailId;
        this.passwordId=passwordId;
        this.nameId=nameId;
        this.pinId=pinId;
    }

    public String getIdtoken(){return Idtoken;}
    public void setIdtoken(String Idtoken){this.Idtoken=Idtoken;}

    public String getEmailId(){return emailId;}
    public void setEmailId(String emailId){this.emailId=emailId;}

    public String getPasswordId(){return passwordId;}
    public void setPasswordId(String passwordId){this.passwordId=passwordId;}

    public String getNameId(){return nameId;}
    public void setNameId(String nameId){this.nameId=nameId;}

    public String getPinId(){return pinId;}
    public void setPinId(String pinId){this.pinId=pinId;}

}
