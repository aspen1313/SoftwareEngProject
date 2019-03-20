package com.example.project.models;

/**
 * Interface for all User classes.
 */
public interface IUser {

    boolean isAdmin();

    boolean hasVotedOnPoll(String pollId);

    String getUsername();

    String getFirestoreId();

}
