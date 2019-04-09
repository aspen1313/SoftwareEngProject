package com.example.project;

import com.example.project.models.IUser;
import com.example.project.models.Poll;
import com.example.project.models.Question;
import com.example.project.models.User;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the User class to ensure functionality.
 */
public class UserTests {
    private IUser user;
    private IUser adminUser;
    private Poll poll;
    private Poll notVotedPoll;
    private String VOTED_ON_POLL_ID = "VOTED_POLL";
    private String NOT_VOTED_POLL_ID = "NOT_VOTED";
    private String USERNAME = "username";

    private String FIRESTORE_ID = "12345ABCDEFG";

    @Before
    public void runBeforeTests(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Option 1");
        options.add("Option 2");
        Question question = Question.getNewQuestion("Test Question", options);
        poll = Poll.getNewElection("Test Poll", question);
        poll.id = VOTED_ON_POLL_ID;

        notVotedPoll = Poll.getNewElection("Test Poll 2", question);
        notVotedPoll.id = NOT_VOTED_POLL_ID;

        user = new User(FIRESTORE_ID, USERNAME, false);
        user.votedOnPoll(poll.id);

        adminUser = new User(FIRESTORE_ID, USERNAME, true);
    }

    @Test
    public void hasVotedOnPollReturnsFalseForPollsNotVotedOn(){
        assertFalse(user.hasVotedOnPoll(NOT_VOTED_POLL_ID));
    }

    @Test
    public void hasVotedOnPollReturnsTrueForPollsVotedOn(){
        assertTrue(user.hasVotedOnPoll(VOTED_ON_POLL_ID));
    }

    @Test
    public void votedOnCanBeCalledMoreThanOnceWithSameID(){
        user.votedOnPoll(VOTED_ON_POLL_ID);
        user.votedOnPoll(VOTED_ON_POLL_ID);
    }

    @Test
    public void getUsernameReturnsCorrectUsername(){
        assertEquals(USERNAME, user.getUsername());
    }

    @Test
    public void isAdminReturnsTrueWhenUserIsAdmin(){
        assertTrue(adminUser.isAdmin());
    }

    @Test
    public void isAdminReturnsFalseWhenUserNotAdmin(){
        assertFalse(user.isAdmin());
    }

    @Test
    public void getFireStoreIDReturnsCorrectID(){
        assertEquals(FIRESTORE_ID, user.getId());
    }
}
