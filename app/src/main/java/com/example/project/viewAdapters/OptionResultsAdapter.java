package com.example.project.viewAdapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project.R;

import java.util.ArrayList;

/**
 * This View Adapter is used in the RecyclerView for the options inside a question.
 */
public class OptionResultsAdapter extends RecyclerView.Adapter<OptionResultsAdapter.OptionViewHolder> {
    /**
     * Data for the view is stored here.
     */
    private ArrayList<String> mDataset;

    /**
     * View holder for each individual option.
     */
    public class OptionViewHolder extends RecyclerView.ViewHolder{
        public TextView optionName;
        public TextView nVotes;

        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            optionName = itemView.findViewById(R.id.optionName);
            nVotes = itemView.findViewById(R.id.nVotes);
        }
    }

    /**
     * Constructor that takes a dataset as an argument.
     * @param dataset
     */
    public OptionResultsAdapter(ArrayList<String> dataset){
        mDataset = dataset;
    }

    /**
     * Runs when a view holder is created. Simply inflates the view for use in the adapter.
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.options_results_view_holder, viewGroup, false);
        return new OptionViewHolder(view);
    }

    /**
     * Runs when the view holder is bound.
     * Currently unused as it gets an override every time it is called.
     * @param optionViewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder optionViewHolder, int i) {
    }

    /**
     * Returns the number of items we have in our data-set.
     * @return
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
