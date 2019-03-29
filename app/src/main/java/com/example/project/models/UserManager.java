package com.example.project.models;

public class UserManager {
    private static User user;

    public static void login(User user){
    }

    public static void logout(){
        user = null;
    }

    public static User getUser(){
        return user;
    }
}