package com.example.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SetTimerActivity extends AppCompatActivity {
    Button btnBack, btnSetTimer;
    EditText edt_length, edt_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settimer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.settimer), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btn_back2);
        btnSetTimer = findViewById(R.id.btn_settimer1);
        edt_length = findViewById(R.id.txt_length);
        edt_message = findViewById(R.id.txt_message);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSetTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length;
                String lengthText = edt_length.getText().toString();
                if (lengthText.isEmpty()) {
                    Toast.makeText(
                            SetTimerActivity.this,
                            "Please enter length of timer",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }
                length = Integer.parseInt(lengthText);
                if (length < 0) {
                    Toast.makeText(SetTimerActivity.this,"Invalid length of time", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);
                intent.putExtra(AlarmClock.EXTRA_LENGTH, length);
                String message = edt_message.getText().toString();
                if (!message.isEmpty()) intent.putExtra(AlarmClock.EXTRA_MESSAGE,message);
                startActivity(intent);
            }
        });
    }
}