package com.example.project.models;

import java.util.Date;
import java.util.HashMap;

/**
 * This abstract class serves as the definition for our Poll class.
 * Votes for different options are tallied in votes, while the names of the corresponding options
 * are stored in options.
 */
public abstract class APoll {
    public int id;
    public HashMap<String, HashMap<String, Integer>> votes;
    public Date startDate;
    public Date endDate;

    /**
     * If this method is implemented, the caller should specify the question to vote on (by name)
     * and which option they want to vote on (also by name) and a vote for that should get recorded.
     * @param question
     * @param option
     */
    public abstract void vote(String question, String option);

    /**
     * Calling this should close the Poll.
     * @param end
     */
    public abstract void close(String end);

    /**
     * Calling this should open the Poll.
     * @param open
     */
    public abstract void open(String open);
}
