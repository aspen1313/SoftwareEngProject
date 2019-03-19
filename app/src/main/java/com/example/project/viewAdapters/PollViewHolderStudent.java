package com.example.project.viewAdapters;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.R;

public class PollViewHolderStudent extends RecyclerView.ViewHolder {
    public TextView titleText;
    public Button viewBut;
    public Button voteProcess;

    public PollViewHolderStudent(View view) {
        super(view);
        titleText = view.findViewById(R.id.pollTitleView);
        viewBut = view.findViewById(R.id.viewButton);
        voteProcess = view.findViewById(R.id.voteProcessButton);
    }
}
