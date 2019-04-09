package com.example.project.models;

import java.io.Serializable;
import java.util.HashSet;

/**
 * User is a class to allow user info to be stored and to enable login
 */
public class User implements IUser, Serializable {

    public String id;
    private String username;
    private HashSet<String> voteSet;
    private boolean admin;

    /**
     * Creating the user requires a firestore ID, username, and if the user being created is an
     * admin or not.
     * @param fireStoreId
     * @param username
     * @param isAdmin
     */
    public User(String fireStoreId, String username, boolean isAdmin){
        this.username = username;
        this.admin = isAdmin;
        this.id = fireStoreId;

        voteSet = new HashSet<>();
    }

    /**
     * A default constructor for compatibility with deserialization.
     */
    private User(){

    }

    @Override
    public String toString(){
        return username;
    }

    /**
     * Returns the admin boolean.
     * @return
     */
    @Override
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Returns true if the user has voted on a poll with ID pollId
     * @param pollId
     * @return
     */
    @Override
    public boolean hasVotedOnPoll(String pollId) {
        return voteSet.contains(pollId);
    }

    /**
     * Returns the user's username.
     * @return
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Returns the user's FireStore ID
     * @return
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Should be called when a user votes on a poll with poll ID.
     * @param pollId
     */
    @Override
    public void votedOnPoll(String pollId) {
        voteSet.add(pollId);
    }
}
