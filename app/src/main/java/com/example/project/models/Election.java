package com.example.project.models;

import com.example.project.Poll;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * The Election class is an implementation of Poll that can be used to gather the aggregate
 * opinions of a group on a given topic.
 */
public class Election extends Poll implements Serializable {
    public String id;
    public boolean isOpen;
    public String title;
    public ArrayList<Question> questions;
    public String sDate;
    public String eDate;

    public Election(){

    }

    private Election(String title, ArrayList<Question> questions){
        isOpen = true;
        this.title = title;
        this.questions = questions;
    }

    private Election(String title, Question question){
        this.title = title;
        questions = new ArrayList<>();
        questions.add(question);
    }

    public static Election getNewElection(String title, ArrayList<Question> questions){
        if (title != null && questions != null){
            if (!title.isEmpty() && !questions.isEmpty()) {
                return new Election(title, questions);
            }
        }
        throw new IllegalArgumentException();
    }

    public static Election getNewElection(String title, Question question){
        if (title != null && question != null){
            if (!title.isEmpty()) {
                return new Election(title, question);
            }
        }
        throw new IllegalArgumentException();
    }

    // TODO implement voting on elections
    @Override
    public void vote(String question, String option) {
        return;
    }

    // TODO implement results retrieval on elections.
    public HashMap<String, HashMap<String, Integer>> getResults(){
        return null;
    }

    public void close(String endDate){
        eDate = endDate;
    }

    public void open(String openDate)
    {
       sDate = openDate;
    }


}
