package com.example.project;

import com.example.project.exceptions.PollNotOpenException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import java.util.HashMap;

public class ElectionTests {
    Election election;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void PollIsCreatedOnSensibleInputs(){
        election = Election.getNewElection("Test Election",
                new String[]{"Option 1", "Option 2"});
        assertNotNull(election);
    }

    @Test
    public void PollIsNotCreatedOnBadDates(){
        exception.expect(IllegalArgumentException.class);
        Election badElection = Election.getNewElection("", new String[]{});
        assertNull(badElection);
    }

    @Test
    public void VotesGetCountedProperly(){
        election.vote(0);
        election.vote(0);
        election.vote(1);

        HashMap<String, Integer> results = election.getResults();

        assertNotNull(results);
        assertEquals((int)results.get(election.options[0]), 2);
        assertEquals((int)results.get(election.options[1]), 1);
    }

    // Tests both the close operation and the refusal. We want to split this up if we can.
    @Test
    public void PollRefusesNewVotesWhenClosed(){
        election.close();

        exception.expect(PollNotOpenException.class);
        election.vote(0);
    }

    @Test
    public void PollAllowsNewVotesWhenOpen(){
        election.open();
        election.vote(0);
    }

}
