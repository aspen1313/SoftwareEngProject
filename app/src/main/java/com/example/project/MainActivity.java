package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button admin;
    private Button student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        admin = findViewById(R.id.adminEntryButton);
        student = findViewById(R.id.studentEntryButton);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminButtonHandler();
            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentButtonHandler();
            }
        });
    }

    /**
     * goes to the student view
     */
    private void studentButtonHandler(){
        Intent intent = new Intent(getApplicationContext(), StudentPage.class);
        startActivity(intent);
    }

    /**
     * goes to the admin view
     */
    private void adminButtonHandler(){
        Intent intent = new Intent(getApplicationContext(), AdminPage.class);
        startActivity(intent);
    }
}
