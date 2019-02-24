package com.example.project;

import com.example.project.exceptions.PollNotOpenException;
import com.example.project.models.Election;
import com.example.project.models.Question;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

public class ElectionTests {
    Election election;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void PollIsCreatedOnSensibleInputs(){
        election = Election.getNewElection("Test Title 1",
                Question.getNewQuestion("Test Question 1",
                        new String[]{"Vote now"}));
        assertNotNull(election);
    }

    @Test
    public void ElectionWithMultipleQuestionsCanBeCreated(){
        Election.getNewElection("Multi Question Election", new Question[]{
                Question.getNewQuestion("Question 1", new String[]{"Vote now"}),
                Question.getNewQuestion("Question 2", new String[]{"Vote now"})
        });
    }

    @Test
    public void PollIsNotCreatedWithNoTitle(){
        exception.expect(IllegalArgumentException.class);
        Election.getNewElection("", new Question[]{
                Question.getNewQuestion("Test Question", new String[]{"Vote now"})});
    }

    @Test
    public void PollIsNotCreateDWithNoQuestion(){
        exception.expect(IllegalArgumentException.class);
        Election.getNewElection("Title", new Question[0]);
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
