package com.example.project;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.project.activities.EditPollActivity;
import com.example.project.activities.EditQuestionActivity;
import com.example.project.models.Poll;
import com.example.project.models.Question;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.rule.ActivityTestRule;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.project.RecyclerViewItemCountAssertion.withItemCount;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.is;

/**
 * Tests the EditPollActivity
 */
public class EditPollUITests {
    private Poll poll;
    private static final int NUMBER_OF_QUESTIONS = 4;
    private static final int NUMBER_OF_OPTIONS = 4;
    private static final String POLL_NAME = "Test Poll";
    private static final String NEW_POLL_NAME = "Edited Poll Name";
    private Intent intent = new Intent();

    @Rule
    public ActivityTestRule<EditPollActivity> rule
            = new ActivityTestRule<>(EditPollActivity.class, false, false);

    /**
     * Sets up the dummy poll that we pass to the activity
     */
    @Before
    public void setup(){
        ArrayList<String> options;
        ArrayList<Question> questions = new ArrayList<>();

        for(int q=0; q < NUMBER_OF_QUESTIONS; q++){
            options = new ArrayList<>();
            for(int o=0; o < NUMBER_OF_OPTIONS; o++){
                options.add("Q"+q+" Option "+o);
            }
            questions.add(Question.getNewQuestion("Question "+q, options));
        }
        poll = Poll.getNewElection(POLL_NAME, questions);
        intent.putExtra("poll", poll);
    }

    /**
     * Tests that the add button is adding an item to the view.
     */
    @Test
    public void AddButtonAddsNewItemToView(){
        rule.launchActivity(intent);
        onView(withId(R.id.addNewQuestionButton)).perform(click());
        onView(withId(R.id.recyclerView)).check(withItemCount(NUMBER_OF_QUESTIONS + 1));
        rule.finishActivity();
    }

    /**
     * Tests that the remove button removes an item from the view.
     */
    @Test
    public void RemoveButtonRemovesItemFromView(){
        rule.launchActivity(intent);
        onView(withId(R.id.removeQuestionButton)).perform(click());
        onView(withId(R.id.recyclerView)).check(withItemCount(NUMBER_OF_QUESTIONS - 1));
        rule.finishActivity();
    }

    /**
     * Tests that the poll name gets set correctly.
     */
    @Test
    public void PollNameGetsPopulatedCorrectly(){
        rule.launchActivity(intent);
        onView(withId(R.id.pollName)).check(matches(withText(POLL_NAME)));
        rule.finishActivity();
    }

    /**
     * Tests that the edit button changes activities correctly.
     */
    @Test
    public void EditButtonGoesToEditQuestionActivity(){
        rule.launchActivity(intent);
        onView(withId(R.id.editQuestion)).perform(click());
        intended(hasComponent(EditQuestionActivity.class.getName()));
        rule.finishActivity();
    }

    /**
     * Tests that that save button writes title to the object..
     */
    @Test
    public void SaveButtonSavesTitle(){
        rule.launchActivity(intent);
        onView(withId(R.id.pollName)).perform(clearText());
        onView(withId(R.id.pollName)).perform(typeText(NEW_POLL_NAME));
        onView(withId(R.id.saveButton)).perform(click());
        assertEquals(EditPollActivity.poll.title, NEW_POLL_NAME);
        rule.finishActivity();
    }

    /**
     * Tests that the cancel button does not write the new title to the object.
     */
    @Test
    public void CancelButtonDoesNotSaveTitle(){
        rule.launchActivity(intent);
        onView(withId(R.id.pollName)).perform(clearText());
        onView(withId(R.id.pollName)).perform(typeText(NEW_POLL_NAME));
        onView(withId(R.id.saveButton)).perform(click());
        assertEquals(EditPollActivity.poll.title, POLL_NAME);
        rule.finishActivity();
    }
}
