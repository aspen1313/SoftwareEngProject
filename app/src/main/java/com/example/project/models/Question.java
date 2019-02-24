package com.example.project.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Question implements Serializable {
    public String title;
    public ArrayList<String> options;
    public ArrayList<Integer> votes;

    private Question(String title, ArrayList<String> options){
        this.title = title;
        this.options = options;
        votes = new ArrayList<>(options.size());
    }

    public static Question getNewQuestion(String title, ArrayList<String> options){
        if (title != null && options != null){
            if (!title.isEmpty() && !options.isEmpty() && !options.get(0).isEmpty()) {
                return new Question(title, options);
            }
        }
        throw new IllegalArgumentException();
    }

    // TODO Implement voting on questions
    public void vote(int voteIndex){
        return;
    }

    // TODO implement results retrieval for questions
    public HashMap<String, Integer> getResults(){
        return null;
    }
}
