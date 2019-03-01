package com.example.project.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * The Question class is used by the Poll class to allow for multiple questions for a single
 * poll / election
 */
public class Question implements Serializable {
    public String title;
    public ArrayList<String> options;
    public ArrayList<Integer> votes;

    private Question(String title, ArrayList<String> options){
        this.title = title;
        this.options = options;
        votes = new ArrayList<>(Collections.nCopies(options.size(), 0));
    }

    /**
     * Returns a new question object with the specified title and options.
     * @param title
     * @param options
     * @return
     */
    public static Question getNewQuestion(String title, ArrayList<String> options){
        if (title != null && options != null){
            if (!title.isEmpty() && !options.isEmpty() && !options.get(0).isEmpty()) {
                return new Question(title, options);
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Allows a question to be voted on using the index of the option that the user chose. Unimplemented.
     * @param voteIndex
     */
    public void vote(int voteIndex){
        return;
    }

    /**
     * Allows the results of a question to be retrieved.
     * @return
     */
    public HashMap<String, Integer> getResults(){
        HashMap<String, Integer> results = new HashMap<String, Integer>();
        String option;
        int voteCount;
        for(int i=0; i<options.size(); i++){
            option = options.get(i);
            voteCount = votes.get(i);

            results.put(option, voteCount);
        }
        return results;
    }
}
