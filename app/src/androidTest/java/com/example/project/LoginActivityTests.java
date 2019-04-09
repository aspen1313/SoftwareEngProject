package com.example.project;

import android.content.Intent;
import android.util.Log;

import com.example.project.activities.LoginActivity;
import com.example.project.managers.UserManager;
import com.example.project.models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

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
    private final String ADMIN_USERNAME = "Admin2_Electric_Boogaloo";

    /**
     * Runs before our tests and sets up the activity for us.
     */
    @Before
    public void setup(){
        intent = new Intent();
    }

    /**
     * Ensures that the user is logged out when the tests finish.
     */
    @After
    public void teardown(){
        UserManager.logout();
    }

    /**
     * Ensures that clicking "logout" does not crash the app when not logged in.
     */
    @Test
    public void logoutButtonDoesNotCatastrophicallyFailWhenNoUserLoggedIn(){
        UserManager.logout();
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
        UserManager.logout();
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
        UserManager.logout();
        rule.launchActivity(intent);
        onView(withId(R.id.userText)).perform(typeText(NONEXISTENT_USERNAME))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        assertNull(UserManager.getUser());
        rule.finishActivity();
    }

    /**
     * Ensures that attempting to login as a valid user succeeds.
     */
    @Test
    public void loginButtonCorrectlyLogsInValidUser(){
        UserManager.logout();
        rule.launchActivity(intent);
        onView(withId(R.id.userText)).perform(typeText(VALID_USERNAME))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            Log.d("ERROR", e.toString());
        }

        assertNotNull(UserManager.getUser());
        rule.finishActivity();
    }

    /**
     * Ensures that the register button does not delete existing user accounts.
     */
    @Test
    public void registerButtonDoesNotOverwriteExistingUser(){
        UserManager.logout();
        rule.launchActivity(intent);
        onView(withId(R.id.userText)).perform(typeText(VALID_USERNAME))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.registerButton)).perform(click());
        assertNull(UserManager.getUser());
        rule.finishActivity();
    }

    /**
     * Ensures that the register button correctly registers a new user.
     */
    @Test
    public void registerButtonCorrectlyRegistersNonexistentUser(){
        UserManager.logout();
        rule.launchActivity(intent);
        onView(withId(R.id.userText)).perform(typeText(NONEXISTENT_USERNAME))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.registerButton)).perform(click());
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            Log.d("ERROR", e.toString());
        }
        assertNotNull(UserManager.getUser());

        // deletes the user so subsequent runs don't fail.
        onView(withId(R.id.deleteUserButton)).perform(click());
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            Log.d("ERROR", e.toString());
        }

        assertNull(UserManager.getUser());

        rule.finishActivity();
    }

    /**
     * Ensures that when the admin check-box is selected that the corresponding user is an admin.
     */
    @Test
    public void adminUserCreatedCorrectly(){
        UserManager.logout();
        rule.launchActivity(intent);
        onView(withId(R.id.userText))
                .perform(typeText(ADMIN_USERNAME))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.adminCheckbox)).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            Log.d("ERROR", e.toString());
        }
        assertNotNull(UserManager.getUser());
        assertTrue(UserManager.getUser().isAdmin());
        onView(withId(R.id.deleteUserButton)).perform(click());
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            Log.d("ERROR", e.toString());
        }
        assertNull(UserManager.getUser());
        rule.finishActivity();
    }
}
