package com.example.project.managers;

import com.example.project.models.User;

/**
 * UserManager follows the singleton pattern to ensure that there is only ever one instance of user
 */
public class UserManager {
    private static User user;

    /**
     * takes a User and assigns them to the static user variable.
     * @param u
     */
    public static void login(User u){
        user = u;
    }

    /**
     * un-assigns the user class.
     */
    public static void logout(){
        user = null;
    }

    /**
     * returns the user instance.
     * @return
     */
    public static User getUser(){
        return user;
    }
}