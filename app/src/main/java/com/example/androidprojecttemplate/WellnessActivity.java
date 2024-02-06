package com.example.androidprojecttemplate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class WellnessActivity extends AppCompatActivity {

    private WellnessViewModel viewModel;
    private EditText sleepEditText;
    private EditText fitnessEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // DO NOT MODIFY
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellness_dashboard);
        viewModel = WellnessViewModel.getInstance();
        sleepEditText = findViewById(R.id.sleepHoursEditText);
        fitnessEditText = findViewById(R.id.fitnessMinutesEditText);
        Button saveButton = findViewById(R.id.saveButton);
        Button toFitnessTimerButton = findViewById(R.id.toFitnessTimerButton);






        // TODO 1: Program the toFitnessTimerButton to trigger and explicit intent
        //  Intent should switch the activity on screen to FitnessActivity when toFitnessTimerButton is clicked
        //  Intent should also pass the values in the WellnessViewModel to FitnessActivity. HINT: see putExtra documentation in Intent
        toFitnessTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WellnessActivity.this, FitnessActivity.class);
                i.putExtra("sleepHours", viewModel.getWellnessData().getSleepHours());
                i.putExtra("fitnessMinutes", viewModel.getWellnessData().getFitnessMinutes());
                startActivity(i);
            }
        });

        // TODO 3: program the saveButton with the saveConfigurationData callback when clicked
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveConfigurationData(sleepEditText, fitnessEditText);
            }
        });
    }




    /**
     * DO NOT MODIFY
     */
    protected void saveConfigurationData(EditText sleepText, EditText fitnessText) {

        int sleepHours = sleepText.getText().toString().equals("") ? 0 : Integer.parseInt(sleepText.getText().toString());
        int fitnessMinutes = fitnessText.getText().toString().equals("") ? 0 : Integer.parseInt(fitnessText.getText().toString());
        viewModel.updateData(sleepHours, fitnessMinutes);
        // Clear the EditText fields
        sleepEditText.setText("");
        fitnessEditText.setText("");

        // Hide the keyboard
        hideKeyboard();
    }


    /**
     * DO NOT MODIFY
     */
    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}