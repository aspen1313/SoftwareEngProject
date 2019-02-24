package com.example.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class Vote extends AppCompatActivity {

    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radioButton1:
                if (checked)
                    // option1
                    break;
            case R.id.radioButton2:
                if (checked)
                    // option2
                    break;
        }
    }
}
