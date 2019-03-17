package com.example.project.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.models.Question;
import com.example.project.viewAdapters.OptionResultsAdapter;

import java.util.ArrayList;

/**
 * A very simple view that shows a detailed breakdown of a specific question.
 * Essentially just a recycler view with each option and the corresponding number of votes.
 */
public class QuestionDetailView extends AppCompatActivity {
    private Question question;
    private TextView titleView;
    private Button backButton;
    private RecyclerView optionsView;

    ArrayList<String> dataset;

    /**
     * Sets one listener and finds our views.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail_view);

        titleView = findViewById(R.id.questionName);
        optionsView = findViewById(R.id.optionsView);
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        populateWithData();
    }

    /**
     * Populates our view(s) with data.
     */
    private void populateWithData(){
        question = (Question)getIntent().getSerializableExtra("question");
        titleView.setText(question.title);
        dataset = question.options;

        configureRecyclerView();
    }

    /**
     * Configures a simple recycler adapter to our view.
     */
    private void configureRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        optionsView.setLayoutManager(layoutManager);

        OptionResultsAdapter adapter = new OptionResultsAdapter(dataset){
            @Override
            public void onBindViewHolder(@NonNull final OptionViewHolder holder, final int i){
                String title = dataset.get(i);
                String nVotes = question.votes.get(i).toString();
                holder.optionName.setText(title);
                holder.nVotes.setText(nVotes);
            }
        };

        optionsView.setAdapter(adapter);
    }
}
