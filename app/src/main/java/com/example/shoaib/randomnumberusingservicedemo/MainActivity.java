package com.example.shoaib.randomnumberusingservicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mStartButton;
    private Button mStopButton;

    private TextView mTextView;

    private Intent serviceIntent;

    private boolean mStopLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartButton = findViewById(R.id.startButton);
        mStopButton = findViewById(R.id.stopButton);
        mTextView = findViewById(R.id.textView);

        mStartButton.setOnClickListener(this);
        mStopButton.setOnClickListener(this);

        serviceIntent = new Intent(getApplicationContext(), MyService.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startButton:
                mStopLoop = true;
                startService(serviceIntent);
                break;
            case R.id.stopButton:
                stopService(serviceIntent);
                break;
        }
    }

    private void testing() {
        int i = 1 + 1;
    }

}   // end class MainActivity
