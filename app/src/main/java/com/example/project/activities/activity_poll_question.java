package com.example.project.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.models.Question;
import com.example.project.viewAdapters.OptionResultsAdapter;

import java.util.ArrayList;

/**
 * activity_poll_question allows students to vote on a question object as part of a Poll.
 */
public class activity_poll_question extends AppCompatActivity {
    private Question question;
    private TextView titleView;
    private Button backButton, voteButton;
    private RadioGroup optionsView;

    ArrayList<String> dataset;
    private Intent intent;

    /**
     * Here we set our listeners to our event handlers, and get references to our UI elements.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_question);

        titleView = findViewById(R.id.questionView);
        optionsView = findViewById(R.id.questionRadioGoup);
        intent = getIntent();


        backButton = findViewById(R.id.backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        voteButton = findViewById(R.id.votebutton);
        voteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                voteButtonHandler();
            }
        });
        populateWithData();
    }

    /**
     * Saves the Question object and vote index and returns to previous activity.
     */
    private void voteButtonHandler(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("question", question);
        resultIntent.putExtra("index", intent.getSerializableExtra("index"));
        resultIntent.putExtra("voteIndex", optionsView.getCheckedRadioButtonId());
        setResult(Activity.RESULT_OK, resultIntent);

        finish();
    }

    /**
     * Populates the UI with the passed Question. One should always have been passed.
     */
    private void populateWithData(){
        question = (Question)getIntent().getSerializableExtra("question");
        titleView.setText(question.title);
        dataset = question.options;

        for (int i = 0; i < question.options.size(); i++) {
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(i);
            rdbtn.setText(question.options.get(i));
            optionsView.addView(rdbtn);
        }
    }
}
