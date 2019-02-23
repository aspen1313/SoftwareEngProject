package com.example.project;

import java.util.Date;
import java.util.HashMap;

/**
 * This abstract class serves as the definition for our poll / election class.
 * Votes for different options are tallied in votes, while the names of the corresponding options
 * are stored in options.
 *
 * To vote for an option the vote(int) method should be called with the index of the selected option(s)
 * as input.
 */
public abstract class Poll {
    public int id;
    public HashMap<String, HashMap<String, Integer>> votes;
    public Date startDate;
    public Date endDate;

    public abstract void vote(String question, String option);

    public abstract void close(String end);

    public abstract void open(String open);
}
