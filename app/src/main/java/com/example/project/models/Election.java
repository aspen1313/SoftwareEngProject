package com.example.project.models;

import java.io.Serializable;
import java.util.ArrayList;
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

    /**
     * Creates an empty Election.
     * Deprecated as it was never supposed to be used. Remove all references to this constructor.
     */
    @Deprecated
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

    /**
     * Checks the parameters and creates a new Election with multiple questions.
     * Throws an exception if the parameters are unacceptable.
     * @param title
     * @param questions
     * @return
     */
    public static Election getNewElection(String title, ArrayList<Question> questions){
        if (title != null && questions != null){
            if (!title.isEmpty() && !questions.isEmpty()) {
                return new Election(title, questions);
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Checks the parameters and creates a new Election object with one question.
     * Throws an exception if the parameters are unacceptable.
     * @param title
     * @param question
     * @return
     */
    public static Election getNewElection(String title, Question question){
        if (title != null && question != null){
            if (!title.isEmpty()) {
                return new Election(title, question);
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Allows a vote to be cast in an election using the title of the question
     * and which option the user chose.
     * @param question The question that the user is voting on
     * @param option The option that the user selected
     * TODO implement voting on elections
     */
    @Override
    public void vote(String question, String option) {
        return;
    }

    /**
     * Allows the results of an entire election to be gathered.
     * @return
     * TODO implement results retrieval on elections.
     */
    public HashMap<String, HashMap<String, Integer>> getResults(){
        return null;
    }

    /**
     * Allows the election to be closed.
     * @param endDate
     * TODO Implement closing of an election.
     */
    public void close(String endDate){
        eDate = endDate;
    }

    /**
     * Allows the election to be opened.
     * @param openDate
     * TODO Implement opening of an election.
     */
    public void open(String openDate)
    {
       sDate = openDate;
    }


}
