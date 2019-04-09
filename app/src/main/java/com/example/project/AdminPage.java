package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.activities.EditPollActivity;
import com.example.project.activities.ViewPollsActivity;
import com.example.project.models.Poll;
import com.example.project.models.Question;

import java.util.ArrayList;

/**
 * The admin page allows the admin to navigate to either create polls or view polls.
 */
public class AdminPage extends AppCompatActivity {

    private Button create;
    private Button view;

    /**
     * Sets up our page with all its views, adds handlers to the buttons.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        create = findViewById(R.id.createElectionButton);
        view = findViewById(R.id.viewElectionsButton);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewPollHandler();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPollsHandler();
            }
        });
    }

    /**
     * Creates a new basic poll object, and starts an activity to edit that activity.
     */
    public void createNewPollHandler(){
        Intent intent = new Intent(getApplicationContext(), EditPollActivity.class);
        ArrayList<String> options = new ArrayList<>();
        options.add("Default Option");
        Poll poll = Poll.getNewElection("Default Title",
                Question.getNewQuestion("Default Question", options));
        intent.putExtra("poll", poll);
        startActivity(intent);
    }

    /**
     * Starts the view poll activity
     */
    public void viewPollsHandler(){
        Intent intent = new Intent(getApplicationContext(), ViewPollsActivity.class);
        startActivity(intent);
    }
}
