package com.example.project;

import java.util.Date;

/**
 * This abstract class serves as the definition for our poll / election class.
 * Votes for different options are tallied in votes, while the names of the corresponding options
 * are stored in options.
 *
 * To vote for an option the vote(int) method should be called with the index of the selected option(s)
 * as input.
 */
public abstract class Poll {
    public String[] options;
    public int[] votes;
    public int id;
    public Date startDate;
    public Date endDate;

    public abstract void vote(int voteIndex);

    public abstract void close();

    public abstract void open();
}
