package com.example.project.models;

public class UserManager {
    private static User user;

    public static void login(String username){
        // Get user with username from firebase and set the user variable.
    }

    public static void logout(){
        return;
    }

    public static User getUser(){
        return user;
    }
}