package com.example.project;

import com.example.project.models.User;
import com.example.project.models.UserManager;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class UserManagerTests {
    private static final String USERNAME = "dummyUsername";

    @Test
    public void loginCorrectlyAssignsUser(){
        UserManager.login("dummyUsername");
        User u = UserManager.getUser();
        assertNotNull(u);
    }

    @Test
    public void logoutCorrectlyVoidsUser(){
        UserManager.login("dummyUsername");
        assertNotNull(UserManager.getUser());
        UserManager.logout();
        assertNull(UserManager.getUser());
    }

    @Test
    public void registerUserCorrectlyCreatesUserObject(){
        User u = UserManager.registerUser(USERNAME, true);
        assertEquals(USERNAME, u.getUsername());
        assertTrue(u.isAdmin());
    }
}
