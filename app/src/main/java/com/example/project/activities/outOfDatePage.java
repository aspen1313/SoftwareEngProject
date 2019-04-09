package com.example.project.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.R;

/**
 * A simple page that we show the user when they cannot vote because the Poll is not open due
 * to the current date not being between its open and close dates.
 */
public class outOfDatePage extends AppCompatActivity {

    /**
     * We only need a back button and it corresponding handlers as this page just displays an error.
     */
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
