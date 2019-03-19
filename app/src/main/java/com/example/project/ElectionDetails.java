package com.example.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.models.Poll;
import com.google.firebase.firestore.FirebaseFirestore;

public class ElectionDetails extends AppCompatActivity {

    private Button open;
    private Button close;
    private Button cancel;
    private Button freeze;
    FirebaseFirestore database;

    Poll e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseFirestore.getInstance();

        setContentView(R.layout.activity_election_details);

        e = (Poll) getIntent().getSerializableExtra("poll");


        open = findViewById(R.id.changeOpenButton);
        close = findViewById(R.id.changeEndButton);
        cancel = findViewById(R.id.cancelChangeButton);
        freeze = findViewById(R.id.freezeButton);

        TextView pollName = (TextView) findViewById(R.id.electionName);
        pollName.setText(e.title);


        TextView viewOpenDate = (TextView) findViewById(R.id.openDateView);
        viewOpenDate.setText(e.sDate);

        TextView viewEndDate = (TextView) findViewById(R.id.endDateView);
        viewEndDate.setText(e.eDate);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHandler();
            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endHandler();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        freeze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                freezeHandler();
            }
        });


    }

    private void openHandler(){
        TextView changeOpen = (TextView) findViewById(R.id.openDateChange);
        String openDate = changeOpen.getText().toString();
        e.open(openDate);
        database.collection("polls").document(e.id).set(e);
        finish();
    }

    private void endHandler(){
        TextView changeEnd = (TextView) findViewById(R.id.closeDateChange);
        String closeDate = changeEnd.getText().toString();
        e.close(closeDate);
        database.collection("polls").document(e.id).set(e);
        finish();
    }

    private  void freezeHandler(){
        e.isOpen = false;
        database.collection("polls").document(e.id).set(e);
        finish();
    }

}
