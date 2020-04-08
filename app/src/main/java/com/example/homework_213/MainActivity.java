package com.example.homework_213;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button ChooseStartDate;
    private Button ChooseEndDate;
    private CalendarView StartDateCalendar;
    private CalendarView EndtDateCalendar;
    private Button okBtn;
    private long StartDate;
    private String StartDateTxt;
    private long EndDate;
    private String EndDateTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        calendarsShow();
        saveDates();
        okListen();
    }

    private void init() {
        ChooseStartDate = findViewById(R.id.chooseStartDate);
        ChooseEndDate = findViewById(R.id.chooseEndDate);
        StartDateCalendar = findViewById(R.id.startDateCalendar);
        EndtDateCalendar = findViewById(R.id.endtDateCalendar);
        okBtn = findViewById(R.id.okBtn);
        StartDateCalendar.setVisibility(View.GONE);
        EndtDateCalendar.setVisibility(View.GONE);
    }

    private void calendarsShow() {
        ChooseStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartDateCalendar.setVisibility(View.VISIBLE);
                EndtDateCalendar.setVisibility(View.GONE);
            }
        });

        ChooseEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EndtDateCalendar.setVisibility(View.VISIBLE);
                StartDateCalendar.setVisibility(View.GONE);
            }
        });
    }

    private void saveDates() {
        StartDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                StartDateTxt = i + "-" + i1 + "-" + i2;
                ChooseStartDate.setText(getString(R.string.startTime) + StartDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(i, i1, i2);
                StartDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });

        EndtDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                EndDateTxt = i + "-" + i1 + "-" + i2;
                ChooseEndDate.setText(getString(R.string.endTime) + EndDateTxt);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.set(i, i1, i2);
                EndDate = gregorianCalendar.getTimeInMillis();
                calendarView.setVisibility(View.GONE);
            }
        });
    }

    private void okListen() {
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StartDate > EndDate) {
                    Toast.makeText(MainActivity.this, R.string.Error, Toast.LENGTH_LONG).show();
                    ChooseStartDate.setText(R.string.startTime);
                    ChooseEndDate.setText(R.string.endTime);
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.start) + StartDateTxt + getString(R.string.finish) + EndDateTxt, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
