package com.example.project.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.models.Question;

import java.util.ArrayList;
import java.util.Collections;

/**
 * EditQuestionActivity allows admins to edit or create a question object as part of a Poll.
 */
public class EditQuestionActivity extends AppCompatActivity {
    private Button saveButton;
    private Button cancelButton;
    private Button addNewItemButton;
    private Button removeItemButton;
    private TextView questionName;

    private LinearLayout optionsView;
    private Intent intent;
    private Question question;

    /**
     * Here we set our listeners to our event handlers, and get references to our UI elements.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);

        questionName = findViewById(R.id.questionName);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);
        addNewItemButton = findViewById(R.id.addNewOptionButton);
        removeItemButton = findViewById(R.id.removeOptionButton);

        optionsView = findViewById(R.id.optionsView);
        intent = getIntent();
        question = (Question)intent.getSerializableExtra("question");

        populateUI();

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                saveButtonHandler();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                cancelButtonHandler();
            }
        });

        addNewItemButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addNewItemButtonHandler();
            }
        });

        removeItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItemButtonHandler();
            }
        });
    }

    /**
     * Populates the UI with the passed Question. One should always have been passed.
     */
    private void populateUI(){
        questionName.setText(question.title);
        EditText text;
        for(int i=0; i<question.options.size(); i++){
            text = new EditText(this);
            text.setText(question.options.get(i));
            optionsView.addView(text);
        }
    }

    /**
     * Saves the Question object and returns to previous activity.
     */
    private void saveButtonHandler(){
        question.title = questionName.getText().toString();
        question.options = new ArrayList<>(Collections.nCopies(optionsView.getChildCount(), ""));

        question.votes = new ArrayList<>(Collections.nCopies(question.options.size(), 0));

        for(int i=0; i < optionsView.getChildCount(); i++){
            View view = optionsView.getChildAt(i);
            if(view instanceof EditText){
                EditText editText = (EditText)view;
                question.options.set(i, editText.getText().toString());
            }
        }

        Intent resultIntent = new Intent();
        resultIntent.putExtra("question", question);
        resultIntent.putExtra("index", intent.getSerializableExtra("index"));
        setResult(Activity.RESULT_OK, resultIntent);

        // For testing purposes ONLY
        Question.setQuestionObject(question);
        finish();
    }

    /**
     * Returns to the previous activity without saving.
     */
    private void cancelButtonHandler(){
        finish();
    }

    /**
     * Add new EditText item to the layout.
     */
    private void addNewItemButtonHandler(){
        int indexToAddNew = optionsView.getChildCount();
        optionsView.addView(new EditText(this), indexToAddNew);
    }

    /**
     * Removes last item from the layout.
     */
    private void removeItemButtonHandler(){
        int indexToRemove = optionsView.getChildCount() - 1;
        if(indexToRemove != -1){
            optionsView.removeViewAt(indexToRemove);
        }
    }
}
