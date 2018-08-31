package com.example.user.neveralone2;

public class User {
    private String user_name;
    private String user_email;
    private String user_password;
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String get_user_email() {
        return user_email;
    }

    public String get_user_password() {
        return user_password;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void set_user_email(String user_email) {
        this.user_email = user_email;
    }

    public void set_user_password(String user_password) {
        this.user_password = user_password;
    }

}
