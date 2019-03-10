package com.example.project;

import android.content.Intent;

import com.example.project.activities.EditPollActivity;
import com.example.project.models.Poll;
import com.example.project.models.Question;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.project.RecyclerViewItemCountAssertion.withItemCount;
import static junit.framework.TestCase.assertEquals;

/**
 * Tests the EditPollActivity
 */
public class EditPollUITests {
    private Poll poll;
    private static final int NUMBER_OF_QUESTIONS = 1;
    private static final int NUMBER_OF_OPTIONS = 4;
    private static final String POLL_NAME = "Test Poll";
    private static final String SAVE_POLL_NAME = "Edited Poll Name";
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
     * Tests that the edit button is clickable.
     * We would like to test if we go to the right view in the future, but espresso is limiting us.
     */
    @Test
    public void EditButtonIsClickable(){
        rule.launchActivity(intent);
        onView(withId(R.id.editQuestionButton)).perform(click());
        rule.finishActivity();
    }

    /**
     * Tests that that save button writes title to the object..
     */
    @Test
    public void SaveButtonSavesTitle(){
        rule.launchActivity(intent);
        onView(withId(R.id.pollName)).perform(clearText());
        onView(withId(R.id.pollName)).perform(typeText(SAVE_POLL_NAME));
        onView(withId(R.id.saveButton)).perform(click());
        assertEquals(SAVE_POLL_NAME, EditPollActivity.poll.title);
        rule.finishActivity();
    }

    /**
     * Tests that the cancel button does not write the new title to the object.
     */
    @Test
    public void CancelButtonDoesNotSaveTitle(){
        rule.launchActivity(intent);
        onView(withId(R.id.pollName)).perform(clearText());
        onView(withId(R.id.pollName)).perform(typeText(SAVE_POLL_NAME));
        onView(withId(R.id.cancelButton)).perform(click());
        assertEquals(POLL_NAME, EditPollActivity.poll.title);
        rule.finishActivity();
    }
}
