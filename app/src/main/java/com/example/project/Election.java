package com.example.project;

import java.util.Date;
import java.util.HashMap;

/**
 * The Election class is an implementation of Poll that can be used to gather the aggregate
 * opinions of a group on a given topic.
 */
public class Election extends Poll{
    private boolean isOpen;
    public String title;
    public String[] options;
    public int[] votes;
    public int id;
    public Date startDate;
    public Date endDate;

    private Election(String title, String[] options){
        isOpen = true;
        this.title = title;
        this.options = options;
    }

    public void vote(int voteIndex){
        throw new UnsupportedOperationException();
    }

    public void close(){
        throw new UnsupportedOperationException();
    }

    public void open(){
        throw new UnsupportedOperationException();
    }

    public static Election getNewElection(String title, String[] options){
        if (title != null && options != null){
            if (!title.isEmpty() && options.length > 0 && !options[0].isEmpty()) {
                return new Election(title, options);
            }
        }
        throw new IllegalArgumentException();
    }

    public HashMap<String, Integer> getResults(){
        throw new UnsupportedOperationException();
    }
}
