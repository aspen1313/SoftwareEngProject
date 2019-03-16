package com.example.project;

import com.example.project.activities.PollResultsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;

public class PollResultsViewTests {
    @Rule
    public ActivityTestRule<PollResultsActivity> rule
            = new ActivityTestRule<>(PollResultsActivity.class, false, false);

    @Before
    public void setup(){

    }

    @Test
    public void titleIsCorrect(){

    }

    @Test
    public void hasCorrectNumberOfItems(){

    }
}
