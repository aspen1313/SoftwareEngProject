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

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void QuestionFailsOnBadInput(){
        ArrayList<String> options = new ArrayList<>();

        exception.expect(IllegalArgumentException.class);
        question = Question.getNewQuestion("", options);

    }

    @Test
    public void QuestionCreatedOnSensibleInput(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Test Option 1");
        options.add("Test Option 2");

        question = Question.getNewQuestion("Test Question", options);
        assertNotNull(question);
    }

    @Test
    public void VotesCountedProperlyOnQuestion(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Option 1");
        options.add("Option 2");
        Question question = Question.getNewQuestion("Question", options);

        question.votes.set(0, 2);
        question.votes.set(1, 9);

        assertEquals((int)question.getResults().get("Option 1"), 2);
        assertEquals((int)question.getResults().get("Option 2"), 9);
    }

    @Test
    public void AddVoteOption1(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Option 1");
        options.add("Option 2");
        Question question = Question.getNewQuestion("Question", options);
        question.votes.set(0, 2);
        question.votes.set(1, 9);

        question.vote(0);
        assertEquals((int)question.getResults().get("Option 1"), 3);
    }

    @Test
    public void AddVoteOption2(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Option 1");
        options.add("Option 2");
        Question question = Question.getNewQuestion("Question", options);

        question.votes.set(0, 2);
        question.votes.set(1, 9);

        question.vote(1);
        assertEquals((int)question.getResults().get("Option 2"), 10);
    }

    @Test
    public void SettingQuestionDoesNotOverwriteExistingQuestion(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Option 1");
        options.add("Option 2");
        Question question = Question.getNewQuestion("Question", options);
        Question question2 = Question.getNewQuestion("Question2", options);

        Question.setQuestionObject(question);
        assertFalse(Question.setQuestionObject(question2));
    }

    @Test
    public void QuestionObjectGetsNulledCorrectlyWhenGetIsCalled(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Option 1");
        options.add("Option 2");
        Question question = Question.getNewQuestion("Question", options);
        Question.setQuestionObject(question);
        
        assertNotNull(Question.getQuestionObject());
        assertNull(Question.getQuestionObject());
    }

    @Test
    public void MostPopularOptionCorrectlyReturned(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Option 1");
        options.add("Option 2");
        Question q = Question.getNewQuestion("Question", options);
        q.votes.set(0,5);
        q.votes.set(1,10);

        assertEquals(q.getMostPopularOption(), "Option 2");
    }
}
