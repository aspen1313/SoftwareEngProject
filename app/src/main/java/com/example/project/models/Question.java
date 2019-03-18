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
    /**
     * The question being posed.
     * example: "What kind of pizza do you want?"
     */
    public String title;

    /**
     * A number of options that can answer the question for voters to choose from.
     * example: Pepperoni, Vegetarian, Cheese
     */
    public ArrayList<String> options;

    /**
     * The number of votes for each corresponding option.
     * Indices here correspond to indices in options.
     */
    public ArrayList<Integer> votes;

    /**
     * A static reference used to pass edited question objects to activities.
     */
    private static Question editingQuestion = null;

    /**
     * Private constructor for serialization compatibility.
     */
    private Question(){
    }

    /**
     * Private constructor used by getNewQuestion only.
     * @param title
     * @param options
     */
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
        for(int i = 0; i < votes.size();i++){
            if(votes.get(i)==1){
                votes.set(i, 0);
            }
        }
            votes.set(voteIndex, votes.get(voteIndex) + 1);
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

    public String getMostPopularOption(){
        int highestCount = -1;
        String currentOption = "";
        for(int i=0; i< options.size(); i++){
            if(votes.get(i) > highestCount){
                currentOption = options.get(i);
            }
        }
        return currentOption;
    }

    /**
     * Works in combination with getQuestionObject to ensure that we never overwrite our question
     * object unless we intended to by calling getQuestionObject first.
     * @param q
     * @return
     */
    public static boolean setQuestionObject(Question q){
        if(editingQuestion == null){
            editingQuestion = q;
            return true;
        }else{
            return false;
        }
    }

    /**
     * Works with setQuestionObject to retrieve the question object so we can pass Question data
     * from a sub-activity to its parent activity.
     * @return
     */
    public static Question getQuestionObject(){
        Question q = editingQuestion;
        editingQuestion = null;
        return q;
    }
}
