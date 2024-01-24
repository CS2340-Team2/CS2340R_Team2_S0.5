package com.example.androidprojecttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FitnessActivity extends AppCompatActivity {

    private TextView timerTextView;
    private TextView sleepHoursTextView;
    private TextView fitnessMinutesTextView;
    private Button startStopButton;



    private Handler timerHandler = new Handler();
    private boolean stopwatchRunning = false;
    private int seconds = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // TODO 5: Set content view to activity_fitness_timer.xml file and assign TextViews and button to XML bindings





        // TODO 6: Retrieve WellnessViewModel data from bundle and set 'sleepHoursTextView' and 'fitnessMinutesTextView' displays to these values




        startStopButton.setOnClickListener(v -> {
            runTimer(); // Don't change or remove this line!

            /*
             * TODO 7: Implement timer logic for stopwatch
             * Text on the 'startStopButton' should change from "Start" to "Stop" and vice versa
             * HINT: look at the implementation of runTimer
             */


        });


        // DO NOT MODIFY
        Button toWellnessDashboardButton = findViewById(R.id.toWellnessDashboardButton);
        toWellnessDashboardButton.setOnClickListener(v -> {
            Intent intent = new Intent(FitnessActivity.this, WellnessActivity.class);
            startActivity(intent);
        });

    }


    /**
     * DO NOT MODIFY!
     */
    private void runTimer() {
        new Thread(() -> {
            while (stopwatchRunning) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                seconds++;
                timerHandler.post(() -> timerTextView.setText(formatTime(seconds)));
            }
        }).start();
    }


    /**
        DO NOT MODIFY!
     */
    private String formatTime(int totalSecs) {
        int hours = totalSecs / 3600;
        int minutes = (totalSecs % 3600) / 60;
        int secs = totalSecs % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }
}
