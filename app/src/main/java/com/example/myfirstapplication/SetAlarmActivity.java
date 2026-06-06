package com.example.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;

public class SetAlarmActivity extends AppCompatActivity {
    Button btnBack, btnSetAlarm;
    EditText edt_hour, edt_minute, edt_label;
    Spinner weekdays;
    CheckBox vibrate;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setalarm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.setalarm), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btn_back1);
        btnSetAlarm = findViewById(R.id.btn_setalarm1);
        edt_hour = findViewById(R.id.txt_hour);
        edt_minute = findViewById(R.id.txt_minute);
        edt_label = findViewById(R.id.txt_label);
        weekdays = findViewById(R.id.spinner);
        vibrate = findViewById(R.id.checkBox);
        radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                if (checkedId == R.id.one_time) weekdays.setEnabled(false);
                else weekdays.setEnabled(true);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour, minute;
                String hourText = edt_hour.getText().toString();
                if (hourText.isEmpty()) {
                    Toast.makeText(
                            SetAlarmActivity.this,
                            "Please enter hour",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }
                hour = Integer.parseInt(hourText);
                String minuteText = edt_minute.getText().toString();
                if (minuteText.isEmpty()) {
                    Toast.makeText(
                            SetAlarmActivity.this,
                            "Please enter minute",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }
                minute = Integer.parseInt(minuteText);

                if (hour < 0 || hour > 23) {
                    Toast.makeText(SetAlarmActivity.this,"Invalid hour", Toast.LENGTH_SHORT).show();
                    return;
                }
                minute = Integer.parseInt(edt_minute.getText().toString());
                if (minute < 0 || minute > 59) {
                    Toast.makeText(SetAlarmActivity.this,"Invalid minute", Toast.LENGTH_SHORT).show();
                    return;
                }
                int selectedID = radioGroup.getCheckedRadioButtonId();
                if (selectedID == -1) {
                    Toast.makeText(SetAlarmActivity.this, "Please specify one-time or weekly alarm", Toast.LENGTH_SHORT).show();
                    return;
                }
                String message = edt_label.getText().toString();

                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
                intent.putExtra(AlarmClock.EXTRA_MINUTES, minute);
                if (!message.isEmpty()) intent.putExtra(AlarmClock.EXTRA_MESSAGE,message);
                if (selectedID == R.id.weekly) {
                    int positon = weekdays.getSelectedItemPosition();
                    ArrayList<Integer> days = new ArrayList<>();
                    switch (positon) {
                        case 0:
                            days.add(Calendar.MONDAY);
                            break;

                        case 1:
                            days.add(Calendar.TUESDAY);
                            break;

                        case 2:
                            days.add(Calendar.WEDNESDAY);
                            break;

                        case 3:
                            days.add(Calendar.THURSDAY);
                            break;

                        case 4:
                            days.add(Calendar.FRIDAY);
                            break;

                        case 5:
                            days.add(Calendar.SATURDAY);
                            break;

                        case 6:
                            days.add(Calendar.SUNDAY);
                            break;
                    }
                    intent.putIntegerArrayListExtra(AlarmClock.EXTRA_DAYS, days);
                }
                intent.putExtra(AlarmClock.EXTRA_VIBRATE, vibrate.isChecked());
                startActivity(intent);
            }
        });
    }
}
