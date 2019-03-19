package com.example.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.project.activities.ViewPollsActivityStudent;

public class StudentPage extends AppCompatActivity {

    private Button view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);


        view = findViewById(R.id.viewElectionsButton);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewStudentPollsHandler();
            }
        });

    }

    /**
     * Starts the view poll activity
     */
    public void viewStudentPollsHandler(){
        Intent intent = new Intent(getApplicationContext(), ViewPollsActivityStudent.class);
        startActivity(intent);
    }

}
