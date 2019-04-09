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

/**
 * Adapter for a recycler view to display Question objects to the students.
 */
public class QuestionResultsAdapter extends RecyclerView.Adapter<QuestionResultsAdapter.QuestionViewHolder> {
    private ArrayList<Question> mDataset;

    /**
     * A view holder that gives the name of the election, a button to look at details, and a view
     * of the currently most popular option.
     */
    public class QuestionViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public Button detailsButton;
        public TextView mostPopular;
        private Question mQuestion;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.questionName);
            detailsButton = itemView.findViewById(R.id.detailsButton);
            mostPopular = itemView.findViewById(R.id.mostPopularText);
        }

        /**
         * Returns the question associated with this view holder so that we may interact with it.
         * @return
         */
        public Question getQuestion(){
            return mQuestion;
        }

        /**
         * Sets thee internal question to the one provided by the parameter.
         * @param question
         */
        public void setQuestion(Question question){
            mQuestion = question;
        }
    }

    /**
     * Runs when the view holder is created. Just inflates the layout.
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.question_results_view_holder, viewGroup, false);
        return new QuestionViewHolder(view);
    }

    /**
     * Runs when the view holder is bound. Unused as we override this each time it needs to be used.
     * @param questionViewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder questionViewHolder, int i) {
    }

    /**
     * Returns the number of items in the adapter's dataset.
     * @return
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    /**
     * Constructor with the data-set as an argument.
     * @param dataSet
     */
    public QuestionResultsAdapter(ArrayList<Question> dataSet){
        mDataset = dataSet;
    }

    /**
     * Gets the i'th element in the dataset.
     * @param i
     * @return
     */
    public Question getFromDataset(int i){
        return mDataset.get(i);
    }
}
