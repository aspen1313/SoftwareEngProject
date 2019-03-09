package com.example.project.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ScrollView;

import com.example.project.QuestionAdapter;
import com.example.project.R;
import com.example.project.models.Poll;
import com.example.project.models.Question;

import java.util.ArrayList;

/**
 * Allows the user to edit a poll.
 */
public class EditPollActivity extends AppCompatActivity {
    public static Poll poll;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;

    private ArrayList<Question> dataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_poll);
        configureRecyclerView();
    }

    /**
     * Sets up our recylcer view
     */
    private void configureRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new QuestionAdapter(dataset);
        recyclerView.setAdapter(mAdapter);
    }
}
