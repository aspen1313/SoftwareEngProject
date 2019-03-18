package com.example.project.activities;

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

public class activity_poll_question extends AppCompatActivity {
    private Question question;
    private TextView titleView;
    private Button backButton, voteButton;
    private RadioGroup optionsView;

    ArrayList<String> dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_question);

        titleView = findViewById(R.id.questionView);
        optionsView = findViewById(R.id.questionRadioGoup);
        backButton = findViewById(R.id.backbutton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        voteButton = findViewById(R.id.votebutton);
        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(optionsView.getCheckedRadioButtonId()!=-1){
                    question.vote(optionsView.getCheckedRadioButtonId());
                    System.out.println(question.votes);
                    finish();
                }
            }
        });
        populateWithData();
    }

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
