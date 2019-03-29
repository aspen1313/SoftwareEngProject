package com.example.project;

import com.example.project.models.User;
import com.example.project.models.UserManager;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Tests the basic functions of the UserManager
 */
public class UserManagerTests {
    private static final String USERNAME = "dummyUsername";
    private static final String FIRESTORE_ID = "12345QWERTY";
    private User dummyUser;

    /**
     * Sets up a dummy user class to use to test
     */
    @Before
    public void runBefore(){
        dummyUser = new User(FIRESTORE_ID, USERNAME, true);
    }

    /**
     * Ensures that the user can be logged in correctly
     */
    @Test
    public void loginCorrectlyAssignsUser(){
        UserManager.login(dummyUser);
        User u = UserManager.getUser();
        assertNotNull(u);
    }

    /**
     * Logout requires login to be working, so we test that first.
     */
    @Test
    public void logoutCorrectlyVoidsUser(){
        UserManager.login(dummyUser);
        assertNotNull(UserManager.getUser());
        UserManager.logout();
        assertNull(UserManager.getUser());
    }
}
