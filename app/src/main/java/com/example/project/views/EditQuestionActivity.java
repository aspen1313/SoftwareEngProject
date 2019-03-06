package com.example.project.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.models.Question;

/**
 * EditQuestionActivity allows admins to edit or create a question object as part of a Poll.
 * */
public class EditQuestionActivity extends AppCompatActivity {
    private Button saveButton;
    private Button cancelButton;
    private Button addNewItemButton;
    private LinearLayout optionsView;
    private Intent intent;
    private Question question;
    private TextView questionName;

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


    }

    /**
     * Populates the UI with the passed Question. One should always have been passed.
     */
    private void populateUI(){
        return;
    }

    private void saveButtonHandler(){
        return;
    }
    private void cancelButtonHandler(){
        return;
    }
    private void addNewItemButtonHandler(){
        return;
    }
}
