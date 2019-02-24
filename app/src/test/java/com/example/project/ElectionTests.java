package com.example.project;

import com.example.project.exceptions.PollNotOpenException;
import com.example.project.models.Election;
import com.example.project.models.Question;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ElectionTests {
    Election election;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void PollIsCreatedOnSensibleInputs(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Vote now");

        election = Election.getNewElection("Test Title 1",
                Question.getNewQuestion("Test Question 1", options));
        assertNotNull(election);
    }

    @Test
    public void ElectionWithMultipleQuestionsCanBeCreated(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Vote now");

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(Question.getNewQuestion("Question 1", options));
        questions.add(Question.getNewQuestion("Question 2", options));

        Election.getNewElection("Multi Question Election", questions);
    }

    @Test
    public void PollIsNotCreatedWithNoTitle(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Vote now");
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(Question.getNewQuestion("Test Question", options));

        exception.expect(IllegalArgumentException.class);
        Election.getNewElection("", questions);
    }

    @Test
    public void PollIsNotCreateDWithNoQuestion(){
        exception.expect(IllegalArgumentException.class);
        Election.getNewElection("Title", new ArrayList<Question>());
    }

    // Tests both the close operation and the refusal. We want to split this up if we can.
    @Test
    public void PollRefusesNewVotesWhenClosed(){
        election.close("10-11-2018");

        exception.expect(PollNotOpenException.class);
        election.vote("Test Question 1", "Vote now");
    }

    @Test
    public void PollAllowsNewVotesWhenOpen(){
        election.open("02-11-2016");
        election.vote("Test Question 1", "Vote now");
    }

}
