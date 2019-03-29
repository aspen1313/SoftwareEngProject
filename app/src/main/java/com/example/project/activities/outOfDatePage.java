package com.example.project.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.R;

public class outOfDatePage extends AppCompatActivity {

    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_of_date_page);

        back = findViewById(R.id.goBackButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backHandler();
            }
        });


    }
    /**
     * Go back to the Student view page
     */
    public void backHandler(){
        finish();
    }
}
