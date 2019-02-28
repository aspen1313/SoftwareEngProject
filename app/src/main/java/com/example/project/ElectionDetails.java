package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.models.Poll;

public class ElectionDetails extends AppCompatActivity {

    private Button open;
    private Button close;
    private Button cancel;

    Poll e = new Poll("2018-12-12", "2019-12-12");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_details);

        open = findViewById(R.id.changeOpenButton);
        close = findViewById(R.id.changeEndButton);
        cancel = findViewById(R.id.cancelChangeButton);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView changeOpen = (TextView) findViewById(R.id.openDateChange);
                String openDate = changeOpen.getText().toString();
                e.open(openDate);
                Intent intent = new Intent(getApplicationContext(),ViewElections.class);
                startActivity(intent);
            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView changeEnd = (TextView) findViewById(R.id.closeDateChange);
                String closeDate = changeEnd.getText().toString();
                e.close(closeDate);
                Intent intent = new Intent(getApplicationContext(),ViewElections.class);
                startActivity(intent);
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewElections.class);
                startActivity(intent);
            }
        });


    }
}
