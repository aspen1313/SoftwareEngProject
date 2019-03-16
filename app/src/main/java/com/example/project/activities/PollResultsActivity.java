package com.example.project.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.models.Poll;
import com.example.project.models.Question;
import com.example.project.viewAdapters.QuestionResultsAdapter;

import java.util.ArrayList;

public class PollResultsActivity extends AppCompatActivity {
    // Views
    private RecyclerView recyclerView;
    private TextView titleView;
    private Button backButton;

    // Data
    private Poll poll;
    private ArrayList<Question> dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll_results);

        findAllViews();
        populateViewsWithData();
        configureRecyclerView();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * This is where all views are found and assigned
     */
    private void findAllViews(){
        backButton = findViewById(R.id.backButton);
        titleView = findViewById(R.id.pollTitle);
        recyclerView = findViewById(R.id.resultsRecycler);
    }

    /**
     * This is where data is retrieved from the passed model.
     */
    private void populateViewsWithData(){
        setTitle("Results Page");
        Intent intent = getIntent();
        poll = (Poll)intent.getSerializableExtra("poll");
        titleView.setText(poll.title);
        dataSet = poll.questions;
    }

    private void configureRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new QuestionResultsAdapter(dataSet){
            // We override this here because we need access to the application context in order
            // for the edit button to operate correctly.
            @Override
            public void onBindViewHolder(@NonNull final QuestionViewHolder holder, final int i){
                holder.setQuestion(getFromDataset(i));
                holder.mostPopular.setText(holder.getQuestion().getMostPopularOption());
                holder.title.setText(holder.getQuestion().title);
                holder.detailsButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        detailButtonHandler(holder.getQuestion());
                    }
                });
            }

            private void detailButtonHandler(Question q){
                Intent intent = new Intent(getApplicationContext(), QuestionDetailView.class);
                intent.putExtra("question", q);
                startActivity(intent);
            }
        };
        recyclerView.setAdapter(adapter);
    }
}
