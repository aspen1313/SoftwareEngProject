package com.example.project;

import com.example.project.models.Question;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
}