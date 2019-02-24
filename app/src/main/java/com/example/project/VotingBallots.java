package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VotingBallots extends AppCompatActivity {

    private Button next;
    private Button cancel;
    private Button done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting_ballots);

        next = findViewById(R.id.nextQuesButton);
        cancel = findViewById(R.id.cancelButtonStudent);
        done = findViewById(R.id.doneVotingButton);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO Create the Election Object and the the Question object
                // and extract the items from the UI to the objects.

                Intent intent = new Intent(getApplicationContext(), VotingBallots.class);
                startActivity(intent);
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO we need to remove the election object from the list

                Intent intent = new Intent(getApplicationContext(), StudentPage.class);
                startActivity(intent);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO submit the election to the firestore or whichever will hold everything.

                Intent intent = new Intent(getApplicationContext(), StudentPage.class);
                startActivity(intent);
            }
        });


    }
}
