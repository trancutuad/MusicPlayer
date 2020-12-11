package com.example.soc_macmini_15.musicplayer.Model;

public class NguoiDung {
    String userName;
    String password;

public NguoiDung(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public NguoiDung(String userName, String password) {
        this.userName = userName;
        this.password = password;



    }

    @Override
    public String toString() {
        return "NguoiDung {"+
                "userName = '"+userName+'\''+
                ", password = ' "+password + '\''+
                '}';
    }
}
