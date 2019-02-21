package com.example.project;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class QuestionTests {
    Question question;

    @Test
    public void QuestionFailsOnBadInput(){

    }

    @Test
    public void QuestionCreatedOnSensibleInput(){
        question = Question.getNewQuestion("Test Election",
                new String[]{"Option 1", "Option 2"});
        assertNotNull(question);
    }

    @Test
    public void VotesGetCountedProperly(){
        question.vote(0);
        question.vote(0);
        question.vote(1);

        HashMap<String, Integer> results = question.getResults();

        assertNotNull(results);
        assertEquals((int)results.get(question.options[0]), 2);
        assertEquals((int)results.get(question.options[1]), 1);
    }
}
