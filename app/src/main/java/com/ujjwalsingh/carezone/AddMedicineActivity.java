package com.ujjwalsingh.carezone;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddMedicineActivity extends AppCompatActivity {
    ImageView backButton;
    TextView c2date;
    TextView days;
    TextView perdays;
    TextView showTime;
    TextView c2weekname;
    Spinner spinner;
    RelativeLayout relativeTime;
    RelativeLayout calenderTextV;
    Spinner daySpinner;
    Spinner perdaySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        days = findViewById(R.id.days);
        perdays = findViewById(R.id.perdays);
        backButton = findViewById(R.id.backButton);
        showTime = findViewById(R.id.showtime);
        relativeTime = findViewById(R.id.relativeTime);
        calenderTextV = findViewById(R.id.calenderText);
        c2date = findViewById(R.id.c2date);
        c2weekname= findViewById(R.id.c2weekname);

        calenderTextV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDateButton();
            }
        });


        relativeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTimeButton();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMedicineActivity.super.onBackPressed();
            }
        });

        spinner =findViewById(R.id.spinner);
        daySpinner =findViewById(R.id.spinnerDay);
        perdaySpinner = findViewById(R.id.spinnerPerd);
        initSpinner();
        initperSpinnerDay();
        initSpinnerDay();

    }

    public void initSpinner(){
        final String[] items = new String[] {"Every Day", "Every Month"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_lay, items);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
              //  items[0] = "One";
                String items = spinner.getSelectedItem().toString();
                if(items.equals("Every Day")) {
                    days.setText("days");
                    perdays.setText("/ per day");
                }
                else  if(items.equals("Every Month")) {
                    days.setText("month");
                    perdays.setText("/ per month");
                }


                Log.i("ferdi", items);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }
    public void initSpinnerDay(){
        final String[] items = new String[] {"7", "1", "2","3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_day, items);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        daySpinner.setAdapter(adapter);

        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                String items = daySpinner.getSelectedItem().toString();
                Log.i("ferdi", items);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }
    public void initperSpinnerDay(){
        final String[] items = new String[] {"1", "2"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_day, items);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        perdaySpinner.setAdapter(adapter);

        perdaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

                String items = perdaySpinner.getSelectedItem().toString();
                Log.i("ferdi", items);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }

    private void handleDateButton() {
        final Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,R.style.datepicker, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);
                String dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();
                String week = DateFormat.format("EEEE",calendar1).toString();
                String cdate = DateFormat.format("d",calendar1).toString();
                if(cdate.length()==1)
                    cdate='0'+cdate;
                String name_of_week = "";
                for(int i=0; i<3; i++)
                    name_of_week= name_of_week+week.charAt(i);

                c2date.setText(cdate);
                c2weekname.setText(name_of_week);

                Log.i("Grisso",week);
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();
    }

    private void handleTimeButton() {
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Log.i("SD", "onTimeSet: " + hour + minute);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);
                String dateText = DateFormat.format("h:mm a", calendar1).toString();
                Log.i("ferd",dateText);
                showTime.setText(dateText);
            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();

    }


}