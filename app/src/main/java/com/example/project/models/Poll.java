package com.example.project.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Poll class is an implementation of APoll that can be used to gather the aggregate
 * opinions of a group on a given topic.
 */
public class Poll extends APoll implements Serializable {
    public String id;
    public boolean isOpen;
    public String title;
    public ArrayList<Question> questions;
    public String sDate;
    public String eDate;

    /**
     * Private constructor for serialization compatibility.
     */
    private Poll(){
    }

    /**
     * Creates an empty Poll.
     * Deprecated as it was never supposed to be used. Remove all references to this constructor.
     */
    @Deprecated
    public Poll(String s, String e){
        sDate = s;
        eDate = e;
    }

    private Poll(String title, ArrayList<Question> questions){
        isOpen = true;
        this.title = title;
        this.questions = questions;
    }

    private Poll(String title, Question question){
        this.title = title;
        questions = new ArrayList<>();
        questions.add(question);
    }

    /**
     * Checks the parameters and creates a new Poll with multiple questions.
     * Throws an exception if the parameters are unacceptable.
     * @param title
     * @param questions
     * @return
     */
    public static Poll getNewElection(String title, ArrayList<Question> questions){
        if (title != null && questions != null){
            if (!title.isEmpty() && !questions.isEmpty()) {
                return new Poll(title, questions);
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Checks the parameters and creates a new Poll object with one question.
     * Throws an exception if the parameters are unacceptable.
     * @param title
     * @param question
     * @return
     */
    public static Poll getNewElection(String title, Question question){
        if (title != null && question != null){
            if (!title.isEmpty()) {
                return new Poll(title, question);
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Allows a vote to be cast in an election using the title of the question
     * and which option the user chose. Unimplemented.
     * @param question The question that the user is voting on
     * @param option The option that the user selected
     */
    @Override
    public void vote(String question, String option) {
        return;
    }

    /**
     * Allows the results of an entire election to be gathered.
     * @return
     */
    public HashMap<String, HashMap<String, Integer>> getResults(){
        HashMap<String, HashMap<String, Integer>> results = new HashMap<>();
        Question q;
        for(int i=0; i<questions.size(); i++){
            q = questions.get(i);
            results.put(q.title, q.getResults());
        }
        return results;
    }

    /**
     * Allows the election to be closed.
     * @param endDate
     */
    public void close(String endDate){
        eDate = endDate;

    }

    /**
     * Allows the election to be opened.
     * @param openDate
     */
    public void open(String openDate)
    {
       sDate = openDate;
    }


}
