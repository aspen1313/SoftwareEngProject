package com.example.project.models;

import com.google.firebase.firestore.FirebaseFirestore;

public class UserManager {
    private static User user;

    public static void login(String username){
        // Get user with username from firebase and set the user variable.
    }

    public static void logout(){
        user = null;
    }

    public static User getUser(){
        return user;
    }

    public static User registerUser(String username, boolean isAdmin){
        return null;
    }
}