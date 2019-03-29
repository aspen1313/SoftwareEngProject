package com.example.project;

import com.example.project.models.User;
import com.example.project.models.UserManager;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UserManagerTests {

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
}
