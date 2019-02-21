package com.example.project;

import java.util.Date;
import java.util.HashMap;

public class Question {
    public String title;
    public String[] options;
    public int[] votes;

    private Question(String title, String[] options){
        this.title = title;
        this.options = options;
    }

    public void vote(int voteIndex){
        throw new UnsupportedOperationException();
    }

    public static Question getNewQuestion(String title, String[] options){
        if (title != null && options != null){
            if (!title.isEmpty() && options.length > 0 && !options[0].isEmpty()) {
                return new Question(title, options);
            }
        }
        throw new IllegalArgumentException();
    }

    public HashMap<String, Integer> getResults(){
        throw new UnsupportedOperationException();
    }
}
