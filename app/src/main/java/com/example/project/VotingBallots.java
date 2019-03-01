package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
                Intent intent = new Intent(getApplicationContext(), VotingBallots.class);
                startActivity(intent);
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudentPage.class);
                startActivity(intent);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudentPage.class);
                startActivity(intent);
            }
        });


    }
     //requires methods for incrementing options, call Question.vote
        public void onRadioButtonClicked(View view) {
            // Is the button now checked?
            boolean checked = ((RadioButton) view).isChecked();

            switch(view.getId()) {
                case R.id.radioButton1:
                    if (checked)
                        // vote(0_);
                        break;
                case R.id.radioButton2:
                    if (checked)
                        // vote(1);
                        break;
            }
        }
}
