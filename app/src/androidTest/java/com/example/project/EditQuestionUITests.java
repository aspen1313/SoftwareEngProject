package com.example.project;

import android.content.Intent;

import com.example.project.models.Question;
import com.example.project.activities.EditQuestionActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static org.junit.Assert.assertEquals;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests the UI for EditQuestionActivity
 */
public class EditQuestionUITests {
    @Rule
    public ActivityTestRule<EditQuestionActivity> rule
            = new ActivityTestRule<>(EditQuestionActivity.class, false, false);

    ArrayList<String> options;
    Question question;
    Intent intent;

    /**
     * Before the test runs we create a dummy question for the UI to use.
     */
    @Before
    public void setup(){
        options = new ArrayList<>();
        options.add("Option 1");
        options.add("Option 2");
        question = Question.getNewQuestion("Test Question 1", options);
        intent = new Intent();
        intent.putExtra("question", question);
    }

    /**
     * Ensures that the layout has the correct number of elements.
     */
    @Test
    public void ViewShouldGetPopulatedWithQuestionText(){
        rule.launchActivity(intent);
        onView(withId(R.id.optionsView)).check(matches(hasChildCount(2)));
        rule.finishActivity();
    }

    /**
     * Ensures that the add button adds a new EditText element to the layout.
     */
    @Test
    public void AddButtonShouldAddNewOption(){
        rule.launchActivity(intent);
        onView(withId(R.id.addNewOptionButton)).perform(click());
        onView(withId(R.id.optionsView)).check(matches(hasChildCount(3)));
    }

    /**
     * Ensures that the save button updates the static question object in Question.
     */
    @Test
    public void SaveButtonShouldSaveObject(){
        String title = "Save Title";
        int expectedNumberOfOptions = question.options.size() + 1;
        rule.launchActivity(intent);
        onView(withId(R.id.addNewOptionButton)).perform(click());
        onView(withId(R.id.questionName)).perform(clearText());
        onView(withId(R.id.questionName)).perform(typeText(title), closeSoftKeyboard());
        onView(withId(R.id.saveButton)).perform(click());
        Question returnedObject = Question.getQuestionObject();
        assertEquals(expectedNumberOfOptions, returnedObject.options.size());
        assertEquals(returnedObject.title, title);
    }

    /**
     * Ensures that the cancel button does not update the passed object and ends the activity.
     */
    @Test
    public void CancelButtonShouldNotSaveObject(){
        String title = "Cancel Title";
        rule.launchActivity(intent);
        int numberOfElementsBefore = question.options.size();

        onView(withId(R.id.addNewOptionButton)).perform(click());
        onView(withId(R.id.questionName)).perform(clearText());
        onView(withId(R.id.questionName)).perform(typeText(title), closeSoftKeyboard());
        onView(withId(R.id.cancelButton)).perform(click());

        assertEquals(numberOfElementsBefore, question.options.size());
        assertNotEquals(question.title, title);
    }

    /**
     * Ensures that the title of the question gets populated
     */
    @Test
    public void TitleShouldGetPopulatedCorrectly(){
        rule.launchActivity(intent);
        onView(withId(R.id.questionName)).check(matches(withText("Test Question 1")));
        rule.finishActivity();
    }

    /**
     * Ensures that the remove button removes an item from the layout.
     */
    @Test
    public void RemoveButtonShouldRemoveItemFromLayout(){
        rule.launchActivity(intent);
        int startingCount = question.options.size();
        onView(withId(R.id.removeOptionButton)).perform(click());
        onView(withId(R.id.optionsView)).check(matches(hasChildCount(startingCount - 1)));
        rule.finishActivity();
    }
}