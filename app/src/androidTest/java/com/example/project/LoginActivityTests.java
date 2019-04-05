package com.example.project;

import android.content.Intent;

import com.example.project.activities.LoginActivity;
import com.example.project.managers.UserManager;
import com.example.project.models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Tests the behaviour of the LoginActivity
 */
public class LoginActivityTests {
    /**
     * This rule allows us to start up the activity independently of any other for testing.
     */
    @Rule
    public ActivityTestRule<LoginActivity> rule
            = new ActivityTestRule<>(LoginActivity.class, false, false);

    private Intent intent;
    private final String NONEXISTENT_USERNAME = "fakeBoi";
    private  final String VALID_USERNAME = "realBoi";

    /**
     * Runs before our tests and sets up the activity for us.
     */
    @Before
    public void setup(){
        intent = new Intent();
    }

    @After
    public void teardown(){
        UserManager.logout();
    }

    /**
     * Ensures that clicking "logout" does not crash the app when not logged in.
     */
    @Test
    public void logoutButtonDoesNotCatastrophicallyFailWhenNoUserLoggedIn(){
        rule.launchActivity(intent);
        onView(withId(R.id.logoutButton)).perform(click());
        assertNull(UserManager.getUser());
        rule.finishActivity();
    }

    /**
     * Ensures that clicking "logout" while logged in results in the user being logged out.
     */
    @Test
    public void logoutButtonCorrectlyLogsOutUser(){
        UserManager.login(new User("123", "Bob_Loblaw", false));
        rule.launchActivity(intent);
        onView(withId(R.id.logoutButton)).perform(click());
        assertNull(UserManager.getUser());
        rule.finishActivity();
    }

    /**
     * Ensures that attempting to login as a nonexistent user does not work.
     */
    @Test
    public void loginButtonDoesNotLoginUserIfDoesNotExist(){
        rule.launchActivity(intent);
        onView(withId(R.id.userText)).perform(typeText(NONEXISTENT_USERNAME));
        onView(withId(R.id.loginButton)).perform(click());
        assertNull(UserManager.getUser());
        rule.finishActivity();
    }

    /**
     * Ensures that attempting to login as a valid user succeeds.
     */
    @Test
    public void loginButtonCorrectlyLogsInValidUser(){
        rule.launchActivity(intent);
        onView(withId(R.id.userText)).perform(typeText(VALID_USERNAME));
        onView(withId(R.id.loginButton)).perform(click());
        assertNotNull(UserManager.getUser());
        rule.finishActivity();
    }

    /**
     * Ensures that the register button does not delete existing user accounts.
     */
    @Test
    public void registerButtonDoesNotOverwriteExistingUser(){
        rule.launchActivity(intent);
        onView(withId(R.id.userText)).perform(typeText(VALID_USERNAME));
        onView(withId(R.id.registerButton)).perform(click());
        assertNull(UserManager.getUser());
        rule.finishActivity();
    }

    /**
     * Ensures that the register button correctly registers a new user.
     */
    @Test
    public void registerButtonCorrectlyRegistersNonexistentUser(){
        rule.launchActivity(intent);
        onView(withId(R.id.userText)).perform(typeText(NONEXISTENT_USERNAME));
        onView(withId(R.id.registerButton)).perform(click());
        assertNotNull(UserManager.getUser());
        rule.finishActivity();
    }
}
