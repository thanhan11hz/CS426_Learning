package com.example.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnSetAlarm, btnSetTimer, btnShowAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSetAlarm = findViewById(R.id.btn_setalarm);
        btnSetTimer = findViewById(R.id.btn_settimer);
        btnShowAlarm = findViewById(R.id.btn_showalarm);

        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, SetAlarmActivity.class);
                startActivity(intent1);
            }
        });

        btnSetTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, SetTimerActivity.class);
                startActivity(intent2);
            }
        });

        btnShowAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent3 = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
               startActivity(intent3);
            }
        });
    }
}