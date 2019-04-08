package com.example.project;

import com.example.project.models.Question;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class QuestionTests {
    Question question;

    public Question question(String title){
        ArrayList<String> options = new ArrayList<>();
        options.add("Option 1");
        options.add("Option 2");
        Question question = Question.getNewQuestion(title, options);

        question.votes.set(0, 2);
        question.votes.set(1, 9);
        return question;
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void QuestionFailsOnBadInput(){
        ArrayList<String> options = new ArrayList<>();

        exception.expect(IllegalArgumentException.class);
        question = Question.getNewQuestion("", options);

    }

    /**
     * Checks to see if values exist for the question.
     */
    @Test
    public void QuestionCreatedOnSensibleInput(){

        question = question("Question");
        assertNotNull(question);
    }

    /**
     * Checks the getResults method.
     */
    @Test
    public void VotesCountedProperlyOnQuestion(){
        question = question("Question");

        assertEquals((int)question.getResults().get("Option 1"), 2);
        assertEquals((int)question.getResults().get("Option 2"), 9);
    }

    /**
     * Checks the vote method.
     */
    @Test
    public void AddVoteOption1(){
        question = question("Question");

        question.vote(0);
        assertEquals((int)question.getResults().get("Option 1"), 3);
    }

    /**
     * Checks the vote method.
     */
    @Test
    public void AddVoteOption2(){
        question = question("Question");

        question.vote(1);
        assertEquals((int)question.getResults().get("Option 2"), 10);
    }

    /**
     * Checks setQuestionObject method to replace an existing question object.
     */
    @Test
    public void SettingQuestionDoesNotOverwriteExistingQuestion(){
        question = question("Question");
        Question question2 = question("Question2");

        Question.setQuestionObject(question);
        assertFalse(Question.setQuestionObject(question2));
    }

    @Test
    public void QuestionObjectGetsNulledCorrectlyWhenGetIsCalled(){
        question = question("Question");
        Question.setQuestionObject(question);

        assertNotNull(Question.getQuestionObject());
        assertNull(Question.getQuestionObject());
    }

    /**
     * Checks the getMostPopularOption method to retrieve the option with the most votes.
     */
    @Test
    public void MostPopularOptionCorrectlyReturned(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Option 1");
        options.add("Option 2");
        Question q = Question.getNewQuestion("Question", options);
        q.votes.set(0,5);
        q.votes.set(1,10);
        assertEquals(q.getMostPopularOption(), "Option 2");
        q.votes.set(0,20);
        assertEquals(q.getMostPopularOption(), "Option 1");
    }

    /**
     * Checks the getQuestionState method to check if its "Single".
     */
    @Test
    public void questionStateSingle(){
        question = question("Question");
        question.setQuestionState("Single");
        assertEquals(question.getQuestionState(), "Single");
    }

    /**
     * Checks the getQuestionState method to check if its "Multiple".
     */
    @Test
    public void questionStateMulti(){
        question = question("Question");
        question.setQuestionState("Multiple");
        assertEquals(question.getQuestionState(), "Multiple");
    }

    /**
     * Checks the removeVote method.
     */
    @Test
    public void removeVote(){
        question = question("Question");
        question.removeVote(1);
        assertEquals((int)question.getResults().get("Option 2"), 8);
    }
}
