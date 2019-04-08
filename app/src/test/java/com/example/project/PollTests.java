package com.example.project;

import com.example.project.models.Poll;
import com.example.project.models.Question;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Tests the Poll class and its methods.
 */
public class PollTests {
    Poll poll;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void PollIsCreatedOnSensibleInputs(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Vote now");

        poll = Poll.getNewElection("Test Title 1",
                Question.getNewQuestion("Test Question 1", options));
        assertNotNull(poll);
    }

    @Test
    public void ElectionWithMultipleQuestionsCanBeCreated(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Vote now");

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(Question.getNewQuestion("Question 1", options));
        questions.add(Question.getNewQuestion("Question 2", options));

        Poll.getNewElection("Multi Question Poll", questions);
    }

    @Test
    public void PollIsNotCreatedWithNoTitle(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Vote now");
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(Question.getNewQuestion("Test Question", options));

        exception.expect(IllegalArgumentException.class);
        Poll.getNewElection("", questions);
    }

    @Test
    public void PollIsNotCreateDWithNoQuestion(){
        exception.expect(IllegalArgumentException.class);
        Poll.getNewElection("Title", new ArrayList<Question>());
    }

    @Test
    public void VotesGetCountedCorrectly(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Option 1");
        options.add("Option 2");
        Question question = Question.getNewQuestion("Test Question 1", options);
        Poll poll = Poll.getNewElection("Test Poll", question);

        poll.questions.get(0).votes.set(0, 5);
        poll.questions.get(0).votes.set(1, 10);

        HashMap<String, HashMap<String, Integer>> results =  poll.getResults();

        assertEquals((int)results.get("Test Question 1").get("Option 1"), 5);
        assertEquals((int)results.get("Test Question 1").get("Option 2"), 10);
    }

    @Test
    public void testTotalNumberOFVotes(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Option 1");
        options.add("Option 2");
        Question question = Question.getNewQuestion("Test Question 1", options);
        Poll poll = Poll.getNewElection("Test Poll", question);

        poll.questions.get(0).votes.set(0, 5);
        poll.questions.get(0).votes.set(1, 5);
        assertEquals(poll.getTotalVotes(),10);

    }
}
