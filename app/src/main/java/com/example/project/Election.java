package com.example.project;

import java.util.Date;
import java.util.HashMap;

/**
 * The Election class is an implementation of Poll that can be used to gather the aggregate
 * opinions of a group on a given topic.
 */
public class Election extends Poll{
    public int id;
    private boolean isOpen;
    public String title;
    public Question[] questions;
    public Date startDate;
    public String sDate;
    public Date endDate;
    public String eDate;

    public Election(){
    }


    private Election(String title, Question[] questions){
        isOpen = true;
        this.title = title;
        this.questions = questions;
    }

    private Election(String title, Question question){
        this.title = title;
        questions = new Question[]{question};
    }

    public static Election getNewElection(String title, Question[] questions){
        if (title != null && questions != null){
            if (!title.isEmpty() && questions.length > 0) {
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

    @Override
    public void vote(String question, String option) {

    }

    public void close(String endDate){
        eDate = endDate;
    }

    public void open(String openDate)
    {
       sDate = openDate;
    }

    public HashMap<String, Integer> getResults(){
        throw new UnsupportedOperationException();
    }
}
