package com.example.project;

import android.content.Intent;
import android.support.test.runner.AndroidJUnit4;
import android.widget.LinearLayout;

import com.example.project.models.Question;
import com.example.project.views.EditQuestionActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class EditQuestionUITests {
    @Rule
    public ActivityTestRule<EditQuestionActivity> rule
            = new ActivityTestRule<>(EditQuestionActivity.class, false, false);

    @Test
    public void ViewShouldGetPopulatedWithQuestionText(){
        ArrayList<String> options = new ArrayList<>();
        options.add("Option 1");
        options.add("Option 2");
        Question question = Question.getNewQuestion("Test Question 1", options);

        Intent intent = new Intent();
        intent.putExtra("question", question);
        rule.launchActivity(intent);

        onView(withId(R.id.optionsView)).check(matches(hasChildCount(2)));
    }
}
