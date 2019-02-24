package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ElectionDetails extends AppCompatActivity {

    private Button open;
    private Button close;
    private Button cancel;

    Election e = new Election();
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
                String openDate = findViewById(R.id.openDateChange).toString();
                e.open(openDate);
                Intent intent = new Intent(getApplicationContext(),ViewElections.class);
                startActivity(intent);
            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String closeDate = findViewById(R.id.closeDateChange).toString();
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
