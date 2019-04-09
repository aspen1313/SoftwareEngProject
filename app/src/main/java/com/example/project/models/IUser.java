package com.example.project.models;

/**
 * Interface for all User classes.
 */
public interface IUser {

    /** Returns true if the User is an admin.*/
    boolean isAdmin();

    /** Returns true if the User has voted on poll with ID pollId*/
    boolean hasVotedOnPoll(String pollId);

    /** Returns the User's username.*/
    String getUsername();

    /** Returns the ID that FireStore gave this object. */
    String getId();

    /** Tells the User that it voted on a specific poll*/
    void votedOnPoll(String pollId);
}
