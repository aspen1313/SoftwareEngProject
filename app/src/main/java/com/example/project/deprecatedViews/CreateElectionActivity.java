package com.example.project.deprecatedViews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.AdminPage;
import com.example.project.R;
import com.example.project.models.Poll;
import com.example.project.models.Question;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CreateElectionActivity extends AppCompatActivity {

    private FirebaseFirestore database;
    private ArrayList<Question> questions;

    private Button next;
    private Button cancel;
    private Button done;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_election);

        intent = getIntent();
        if(intent.getSerializableExtra("questions") != null){
            questions = (ArrayList<Question>)intent.getSerializableExtra("questions");
        }
        database = FirebaseFirestore.getInstance();

        next = findViewById(R.id.nextButton);
        cancel = findViewById(R.id.cancelButtonStudent);
        done = findViewById(R.id.doneButton);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectDataIntoQuestion();
                Intent intent = new Intent(getApplicationContext(), CreateElectionActivity.class);
                    intent.putExtra("questions", questions);
                startActivity(intent);
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectDataIntoQuestion();

                Poll poll = Poll.getNewElection("Placeholder Title", questions);

                DocumentReference docRef = database.collection("elections").document();
                poll.id = docRef.getId();
                docRef.set(poll);

                Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                startActivity(intent);
                //finish();
            }
        });
    }

    private void collectDataIntoQuestion(){
        TextView question = findViewById(R.id.editQuestion);
        TextView choice1 = findViewById(R.id.choiceText1);
        TextView choice2 = findViewById(R.id.choiceText2);
        TextView choice3 = findViewById(R.id.choiceText3);
        TextView choice4 = findViewById(R.id.choiceText4);
        TextView choice5 = findViewById(R.id.choiceText5);

        ArrayList<String> options = new ArrayList<>();
        options.add(choice1.getText().toString());
        options.add(choice2.getText().toString());
        options.add(choice3.getText().toString());
        options.add(choice4.getText().toString());
        options.add(choice5.getText().toString());

        Question q = Question.getNewQuestion(question.getText().toString(), options);
        if(questions == null){
            questions = new ArrayList<>();
        }
        questions.add(q);
    }
}
