package com.example.project.models;

import java.io.Serializable;
import java.util.HashSet;

public class User implements IUser, Serializable {

    private String id;
    private String username;
    private HashSet<String> voteSet;
    private boolean isAdmin;

    public User(String fireStoreId, String username, boolean isAdmin){
        this.username = username;
        this.isAdmin = isAdmin;
        this.id = fireStoreId;

        voteSet = new HashSet<>();
    }

    @Override
    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public boolean hasVotedOnPoll(String pollId) {
        return voteSet.contains(pollId);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getFirestoreId() {
        return id;
    }

    @Override
    public void votedOnPoll(String pollId) {
        voteSet.add(pollId);
    }
}
