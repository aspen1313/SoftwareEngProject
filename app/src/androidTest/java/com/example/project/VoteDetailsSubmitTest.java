package com.example.project;

import android.content.Intent;

import com.example.project.activities.QuestionDetailView;
import com.example.project.activities.activity_poll_question;
import com.example.project.models.Question;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Tests QuestionDetailView
 */
public class VoteDetailsSubmitTest {
    private static final String QUESTION_TITLE = "Dummy Title";
    private static final int N_OPTIONS = 5;

    private Intent intent;
    ActivityTestRule<activity_poll_question > rule
            = new ActivityTestRule<>(activity_poll_question .class, false, false);
    private ArrayList<String> options;

    /**
     * Performs some basic setup by creating dummy objects.
     */
    @Before
    public void setup(){
        options = new ArrayList<>();
        for(int i=0; i<N_OPTIONS; i++){
            options.add("Option Number " + (i+1));
        }
        Question question = Question.getNewQuestion(QUESTION_TITLE, options);

        Random rand = new Random();
        for(int i=0; i<N_OPTIONS; i++){
            question.votes.set(i, rand.nextInt(500));
        }

        intent = new Intent();
        intent.putExtra("question", question);
    }

    /**
     * Ensures the title gets populated correctly.
     */
    @Test
    public void TitleGetsPopulatedCorrectly(){
        rule.launchActivity(intent);
        onView(withId(R.id.questionView)).check(matches(withText(QUESTION_TITLE)));
        rule.finishActivity();
    }

    /**
     * Tests for correct number of radio buttons
     */
    @Test
    public void CorrectNumberOfOptionsInView(){
        rule.launchActivity(intent);
        onView(withId(R.id.questionRadioGoup)).check(matches(hasChildCount(N_OPTIONS)));
        rule.finishActivity();
    }
    /**
     * Tests that vote button functions
     */
    @Test
    public void VoteFunctions(){
        rule.launchActivity(intent);
        onView(withId(R.id.votebutton)).perform(click());
        rule.finishActivity();
    }
    /**
     * Tests that cancel button functions
     */
    @Test
    public void CancelFunctions(){
        rule.launchActivity(intent);
        onView(withId(R.id.backbutton)).perform(click());
        rule.finishActivity();
    }
}
