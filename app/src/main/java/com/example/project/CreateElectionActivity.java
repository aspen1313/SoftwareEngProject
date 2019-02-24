package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CreateElectionActivity extends AppCompatActivity {

    private Button next;
    private Button cancel;
    private Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_election);

        next = findViewById(R.id.nextButton);
        cancel = findViewById(R.id.cancelButtonStudent);
        done = findViewById(R.id.doneButton);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView question = findViewById(R.id.editQuestion);
                TextView choice1 = findViewById(R.id.choiceText1);
                TextView choice2 = findViewById(R.id.choiceText2);
                TextView choice3 = findViewById(R.id.choiceText3);
                TextView choice4 = findViewById(R.id.choiceText4);
                TextView choice5 = findViewById(R.id.choiceText5);


                //TODO Create the Election Object and the the Question object
                // and extract the items from the UI to the objects.



                question.setText(null);
                choice1.setText(null);
                choice2.setText(null);
                choice3.setText(null);
                choice4.setText(null);
                choice5.setText(null);
                Intent intent = new Intent(getApplicationContext(), CreateElectionActivity.class);
                startActivity(intent);
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO we need to remove the election object from the list

                Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                startActivity(intent);
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO submit the election to the firestore or whichever will hold everything.

                Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                startActivity(intent);
            }
        });
    }
}
