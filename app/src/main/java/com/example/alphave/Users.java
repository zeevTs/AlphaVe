package com.example.alphave;

public class Users {
    private String userName;
    private String password;
    private String email;
    private String id;

    public Users(String userName, String password, String email, String id) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.id = id;
    }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setPhoneNum(String id) {
        this.id = id;
    }

}
