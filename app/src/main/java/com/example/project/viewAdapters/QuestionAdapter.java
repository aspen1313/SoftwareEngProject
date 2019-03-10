package com.example.project.viewAdapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.models.Question;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>{

    private ArrayList<Question> mDataset;

    /**
     * View holder for the Question View.
     * We don't really need this except in the context of an adapter, so it is defined in here.
     */
    public static class QuestionViewHolder extends RecyclerView.ViewHolder{
        public TextView text;
        public Button editButton;

        public QuestionViewHolder(View view){
            super(view);
            text = view.findViewById(R.id.textView);
            editButton = view.findViewById(R.id.editQuestionButton);
        }
    }

    /**
     * Creates a new QuestionAdapter by setting the dataset.
     * @param questions
     */
    public QuestionAdapter(ArrayList<Question> questions){
        mDataset = questions;
    }

    /**
     * Retrieves the proper view from the layout folder and sets it.
     * @param parent
     * @param i
     * @return
     */
    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_view_holder, parent, false);

        return new QuestionViewHolder(view);
    }

    /**
     * Sets the appropriate data when the view is bound.
     * @param holder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int i) {
        holder.text.setText(mDataset.get(i).title);
    }

    /**
     * Gets the number of items in the dataset.
     * @return
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    /**
     * Returns a single item at index i from the dataset.
     * @param i
     * @return
     */
    public Question getFromDataset(int i){
        return mDataset.get(i);
    }
}
