package com.example.project;

import android.content.Intent;

import com.example.project.activities.PollResultsActivity;
import com.example.project.activities.VoteQuestionActivity;
import com.example.project.models.Poll;
import com.example.project.models.Question;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Tests PollResultsActivity
 */
public class VoteSubmitTest {
    @Rule
    public ActivityTestRule<VoteQuestionActivity> rule
            = new ActivityTestRule<>(VoteQuestionActivity.class, false, false);

    private Poll poll;
    private Intent intent;
    private int TIMES_TO_TEST = 5;
    private String POLL_TITLE = "Poll Title";

    @Before
    public void setup(){
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<String> options = new ArrayList<>();
        options.add("Option 1");
        questions.add(Question.getNewQuestion("Question 1", options));
        poll = Poll.getNewElection(POLL_TITLE, questions);
        intent = new Intent();
        intent.putExtra("poll", poll);
    }

    /**
     Tests that title is correct
     */
    @Test
    public void titleIsCorrect(){
        rule.launchActivity(intent);
        onView(withId(R.id.pollTitle)).check(matches(withText(POLL_TITLE)));
        rule.finishActivity();
    }
    /**
    Tests that submit button functions correctly
     */
    @Test
    public void SubmitFunctions(){
        rule.launchActivity(intent);
        onView(withId(R.id.submitButton)).perform(click());
        rule.finishActivity();
    }
    /**
    Test that cancel button functions correctly
     */
    @Test
    public void CancelFunctions(){
        rule.launchActivity(intent);
        onView(withId(R.id.backButton)).perform(click());
        rule.finishActivity();
    }

    /**
     Ensures the view has the correct number of items.
     */
    @Test
    public void hasCorrectNumberOfItems(){
        for(int j=1; j<TIMES_TO_TEST + 1; j++){
            Poll p = getPollWithNQuestions(POLL_TITLE, j);
            Intent i = new Intent();
            i.putExtra("poll", p);
            rule.launchActivity(i);
            onView(withId(R.id.resultsRecycler)).check(matches(hasChildCount(j)));
            rule.finishActivity();
        }
    }

    /**
     * Helper method to get a poll with a certain number of questions.
     * @param pollTitle
     * @param n
     * @return
     */
    private Poll getPollWithNQuestions(String pollTitle, int n){
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<String> options = new ArrayList<>();
        options.add("Option 1");
        options.add("Option 2");

        for(int i=0; i<n; i++){
            questions.add(Question.getNewQuestion("Question " + i, options));
        }

        return Poll.getNewElection(pollTitle, questions);
    }


}
