package com.ujjwalsingh.carezone;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.ujjwalsingh.carezone.DataBase.Note;
import com.ujjwalsingh.carezone.ui.ViewModel.NoteViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.List;

public class AddMedicineActivity extends AppCompatActivity {
    public static final String EXTRA_NAME1 = "com.ujjwalsingh.carezone.EXTRA_NAMEone";
    public static final String EXTRA_TIME1 = "com.ujjwalsingh.carezone.EXTRA_TIMEone";
    public static final String EXTRA_MINBEFORE1 = "com.ujjwalsingh.carezone.EXTRA_MINBEFOREone";
    public static final String EXTRA_FOODPATTERN1 = "com.ujjwalsingh.carezone.EXTRA_FOODPATTERNone";
    public static final String EXTRA_IMAGE1 = "com.ujjwalsingh.carezone.EXTRA_IMAGEone";
    public boolean isCheckedDone = false;

    public static final String EXTRA_NAME2 = "com.ujjwalsingh.carezone.EXTRA_NAMEtwo";
    public static final String EXTRA_TIME2 = "com.ujjwalsingh.carezone.EXTRA_TIMEtwo";
    public static final String EXTRA_MINBEFORE2 = "com.ujjwalsingh.carezone.EXTRA_MINBEFOREtwo";
    public static final String EXTRA_FOODPATTERN2 = "com.ujjwalsingh.carezone.EXTRA_FOODPATTERNtwo";
    public static final String EXTRA_IMAGE2 = "com.ujjwalsingh.carezone.EXTRA_IMAGEtwo";

    public static final String EXTRA_NAME3 = "com.ujjwalsingh.carezone.EXTRA_NAMEthird";
    public static final String EXTRA_TIME3 = "com.ujjwalsingh.carezone.EXTRA_TIMEonethird";
    public static final String EXTRA_MINBEFORE3 = "com.ujjwalsingh.carezone.EXTRA_MINBEFOREthird";
    public static final String EXTRA_FOODPATTERN3 = "com.ujjwalsingh.carezone.EXTRA_FOODPATTERNthird";
    public static final String EXTRA_IMAGE3 = "com.ujjwalsingh.carezone.EXTRA_IMAGEthird";

    public static final String TOTAL_NUMBER_OF_PILLS = "com.ujjwalsingh.carezone.TOTAL_NUMBER_OF_PILLS";
    public Intent data = new Intent();

    private NoteViewModel noteViewModel;
    public boolean triggerTick;
    public boolean foodcheck1;
    public boolean foodcheck2;
    public boolean foodcheck3;

    public boolean pillcheck1 = true;
    public boolean pillcheck2;
    public boolean pillcheck3;
    SharedPreferences shared1;
    SharedPreferences shared2;
    SharedPreferences shared3;

    //    SharedPreferences shared2 = getSharedPreferences(sharedPfor2, Context.MODE_PRIVATE);
//    SharedPreferences shared3 = getSharedPreferences(sharedPfor3, Context.MODE_PRIVATE);
    Drawable p1;
    Drawable p2;
    Drawable p3;
    ConstraintLayout primelayout;
    boolean pillExp1 = false;
    boolean pillExp2 = false;
    boolean pillExp3 = false;

    String setTimeMinBefore1 = "";
    int foodPattern1 = 0;
    String selectedTime1 = "";
    String pillsAtOnce1 = "";

    String setTimeMinBefore2 = "";
    int foodPattern2 = 0;
    String selectedTime2 = "";
    String pillsAtOnce2 = "";

    String setTimeMinBefore3 = "";
    int foodPattern3 = 0;
    String selectedTime3 = "";
    String pillsAtOnce3 = "";


    static final String sharedPfor1 = "sharedPfor1";
    static final String sharedPfor2 = "sharedPfor2";
    static final String sharedPfor3 = "sharedPfor3";


    static final String time1 = "time1";
    static final String time2 = "time2";
    static final String time3 = "time3";

    static final String food1t = "food1t";
    static final String food2t = "food2t";
    static final String food3t = "food3t";

    static final String minb1 = "minb1";
    static final String minb2 = "minb2";
    static final String minb3 = "minb3";

    static final String nopill1 = "nopill1";
    static final String nopill2 = "nopill2";
    static final String nopill3 = "nopill3";


    MaterialCardView maincard;
    CardView cardView;
    ImageView backButton;
    TextView c2date;
    TextView days;
    TextView perdays;
    TextView pillsAtaTime;
    Button pill2;
    Button pill1;
    Button pill3;
    LottieAnimationView checkButton;
    Button add_medicine_pill;

    EditText medicine_name;

    TextView showTime;
    TextView minBefore;
    TextView c2weekname;
    Spinner spinner;
    Spinner spinnerMinBefore;

    RelativeLayout relativeTime;
    RelativeLayout calenderTextV;
    RelativeLayout food1;
    RelativeLayout food2;
    RelativeLayout food3;
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
        medicine_name = findViewById(R.id.medicine_name);
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
            }
        });
        add_medicine_pill = findViewById(R.id.add_medicine_pill);

        shared1 = getSharedPreferences(sharedPfor1, Context.MODE_PRIVATE);
        shared2 = getSharedPreferences(sharedPfor2, Context.MODE_PRIVATE);
        shared3 = getSharedPreferences(sharedPfor3, Context.MODE_PRIVATE);

        SharedPreferences myPrefs = this.getSharedPreferences(sharedPfor1, MODE_PRIVATE);
        myPrefs.edit().remove(sharedPfor1).apply();
        myPrefs.edit().clear().apply();
        myPrefs.edit().apply();

        SharedPreferences myPrefs2 = this.getSharedPreferences(sharedPfor2, MODE_PRIVATE);
        myPrefs2.edit().remove(sharedPfor2).apply();
        myPrefs2.edit().clear().apply();
        myPrefs2.edit().apply();

        SharedPreferences myPrefs3 = this.getSharedPreferences(sharedPfor3, MODE_PRIVATE);
        myPrefs3.edit().remove(sharedPfor3).apply();
        myPrefs3.edit().clear().apply();
        myPrefs3.edit().apply();

        checkButton = findViewById(R.id.checkButton);
        days = findViewById(R.id.days);
        perdays = findViewById(R.id.perdays);
        backButton = findViewById(R.id.backButton);
        minBefore = findViewById(R.id.minBefore);
        minBefore.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        pillsAtaTime = findViewById(R.id.pillsAtaTime);
        primelayout = findViewById(R.id.primelayout);
        maincard = findViewById(R.id.mealCard);
        cardView = findViewById(R.id.card);
        food1 = findViewById(R.id.food1);
        food2 = findViewById(R.id.food2);
        food3 = findViewById(R.id.food3);
//
//        food1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        showTime = findViewById(R.id.showtime);
        relativeTime = findViewById(R.id.relativeTime);
        calenderTextV = findViewById(R.id.calenderText);
        c2date = findViewById(R.id.c2date);
        c2weekname = findViewById(R.id.c2weekname);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 0);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int w = c.get(Calendar.DAY_OF_WEEK);


        c2date.setText(String.valueOf(day));
        if (w == 1)
            c2weekname.setText("sun");
        else if (w == 2)
            c2weekname.setText("mon");
        else if (w == 3)
            c2weekname.setText("tue");
        else if (w == 4)
            c2weekname.setText("wed");
        else if (w == 5)
            c2weekname.setText("thu");
        else if (w == 6)
            c2weekname.setText("fri");
        else if (w == 7)
            c2weekname.setText("sat");


        pill2 = findViewById(R.id.numberTwoPill);
        pill3 = findViewById(R.id.numberThreePill);
        pill1 = findViewById(R.id.numberOnepill);

        p1 = pill1.getBackground();
        p2 = pill2.getBackground();
        p3 = pill3.getBackground();
        // pill1.setBackgroundResource(R.drawable.whiteborder);


        food1.setTag(food1.getBackground());
        food2.setTag(food2.getBackground());
        food3.setTag(food3.getBackground());

        pill1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pill1.setBackground(getResources().getDrawable(R.drawable.whiteborder));
                pill2.setBackground(p2);
                pill3.setBackground(p3);
                pillcheck1 = true;
                pillcheck2 = false;
                pillExp1 = true;
                pillExp2 = false;
                pillExp3 = false;
                retrieve1();
                pillcheck3 = false;
                cardView.setCardBackgroundColor(Color.parseColor("#74D2DB"));
            }
        });
        pill2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pill2.setBackground(getResources().getDrawable(R.drawable.whiteborder));
                pill1.setBackground(p1);
                pill3.setBackground(p3);
                pillcheck1 = false;
                pillExp1 = false;
                pillExp2 = true;
                pillExp3 = false;
                pillcheck2 = true;
                retrieve2();
                pillcheck3 = false;
                cardView.setCardBackgroundColor(Color.parseColor("#70C1DE"));

            }
        });
        pill3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pill3.setBackground(getResources().getDrawable(R.drawable.whiteborder));
                pill2.setBackground(p2);
                pill1.setBackground(p1);
                pillcheck1 = false;
                pillcheck2 = false;
                pillcheck3 = true;
                pillExp1 = false;
                pillExp2 = false;
                pillExp3 = true;
                retrieve3();
                cardView.setCardBackgroundColor(Color.parseColor("#77ACE5"));

            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                if (isCheckedDone) {
                    checkButton.setSpeed(1.5f);
                    checkButton.playAnimation();
                    isCheckedDone = false;
                } else {
                    checkButton.setSpeed(1.5f);
                    checkButton.playAnimation();
                    isCheckedDone = true;
                }

                Log.i("Kase", "SD");
                ObjectAnimator animator = ObjectAnimator.ofArgb(maincard, "strokeColor", Color.parseColor("#01B302"));
                animator.setDuration(700);
                animator.setRepeatCount(1);
                animator.setRepeatMode(ValueAnimator.REVERSE);
                animator.start();
                setSnackBar();
                pillsAtOnce1 = pillsAtaTime.getText().toString();
                pillsAtOnce3 = pillsAtaTime.getText().toString();
                pillsAtOnce2 = pillsAtaTime.getText().toString();

                Log.i("pill1", String.valueOf(pillcheck1));
                Log.i("pill2", String.valueOf(pillcheck2));
                Log.i("pill3", String.valueOf(pillcheck3));
                if (pillcheck1) {
                    Log.i("Creata", "Sd");
                    save1(selectedTime1, pillsAtOnce1, foodPattern1, setTimeMinBefore1);
                } else if (pillcheck2) {
                    Log.i("Creata", "Sd");
                    save2(selectedTime2, pillsAtOnce2, foodPattern2, setTimeMinBefore2);
                } else if (pillcheck3) {
                    Log.i("Creata", "Sd");
                    save3(selectedTime3, pillsAtOnce3, foodPattern3, setTimeMinBefore3);
                }
                if (pillcheck1) {
                    String name1 = medicine_name.getText().toString();
                    String time1 = showTime.getText().toString();
                    String minbefore1 = spinnerMinBefore.getSelectedItem().toString();
                    int foodpattern1 = 0;
                    if (foodcheck1)
                        foodpattern1 = 1;
                    else if (foodcheck2)
                        foodpattern1 = 2;
                    else if (foodcheck3)
                        foodpattern1 = 3;
                    int image1 = foodpattern1;

                    data.putExtra(EXTRA_NAME1, name1);
                    data.putExtra(EXTRA_TIME1, time1);
                    data.putExtra(EXTRA_MINBEFORE1, minbefore1);
                    data.putExtra(EXTRA_FOODPATTERN1, foodpattern1);
                    data.putExtra(EXTRA_IMAGE1, image1);
                    Log.i("drunk1", "Erer");
                }

                if (pillcheck2) {
                    String name2 = medicine_name.getText().toString();
                    String time2 = showTime.getText().toString();
                    String minbefore2 = spinnerMinBefore.getSelectedItem().toString();
                    int foodpattern2 = 0;
                    if (foodcheck1)
                        foodpattern2 = 1;
                    else if (foodcheck2)
                        foodpattern2 = 2;
                    else if (foodcheck3)
                        foodpattern2 = 3;
                    int image2 = foodpattern2;

                    data.putExtra(EXTRA_NAME2, name2);
                    data.putExtra(EXTRA_TIME2, time2);
                    data.putExtra(EXTRA_MINBEFORE2, minbefore2);
                    data.putExtra(EXTRA_FOODPATTERN2, foodpattern2);
                    data.putExtra(EXTRA_IMAGE2, image2);
                    Log.i("drunk2", "Erer");
                }
                if (pillcheck3) {
                    String name3 = medicine_name.getText().toString();
                    String time3 = showTime.getText().toString();
                    String minbefore3 = spinnerMinBefore.getSelectedItem().toString();
                    int foodpattern3 = 0;
                    if (foodcheck1)
                        foodpattern3 = 1;
                    else if (foodcheck2)
                        foodpattern3 = 2;
                    else if (foodcheck3)
                        foodpattern3 = 3;
                    int image3 = foodpattern3;
                    data.putExtra(EXTRA_NAME3, name3);
                    data.putExtra(EXTRA_TIME3, time3);
                    data.putExtra(EXTRA_MINBEFORE3, minbefore3);
                    data.putExtra(EXTRA_FOODPATTERN3, foodpattern3);
                    data.putExtra(EXTRA_IMAGE3, image3);
                }
            }
        });


//        minBefore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setMinBeforeText();
//                Log.i("serts","SDSd");
//
//            }
//        });
        setFood1();
        setFood2();
        setFood3();


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
               // onBackPressed();
                AddMedicineActivity.super.onBackPressed();

            }
        });


        spinner = findViewById(R.id.spinner);
        daySpinner = findViewById(R.id.spinnerDay);
        perdaySpinner = findViewById(R.id.spinnerPerd);
        spinnerMinBefore = findViewById(R.id.spinnerMinBefore);
        initSpinner();
        initperSpinnerDay();
        initSpinnerDay();
        initSpinnerMinBefore();

        add_medicine_pill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

    }

    public void save1(String ts, String ps, int fps, String mbs) {
        ts = selectedTime1;
        ps = pillsAtOnce1;
        fps = foodPattern1;
        mbs = setTimeMinBefore1;
        SharedPreferences.Editor editor = shared1.edit();
        editor.putString(time1, ts);
        editor.putString(minb1, mbs);
        editor.putInt(food1t, fps);
        editor.putString(nopill1, ps);
        editor.apply();
        Log.i("Creatas", "Mok");

    }

    public void save2(String ts, String ps, int fps, String mbs) {
        ts = selectedTime2;
        ps = pillsAtOnce2;
        fps = foodPattern2;
        mbs = setTimeMinBefore2;
        SharedPreferences.Editor editor2 = shared2.edit();
        editor2.putString(time2, ts);
        editor2.putString(minb2, mbs);
        editor2.putInt(food2t, fps);
        editor2.putString(nopill2, ps);
        editor2.apply();
        Log.i("Creatas", "Mok");
    }

    public void save3(String ts, String ps, int fps, String mbs) {
        ts = selectedTime3;
        ps = pillsAtOnce3;
        fps = foodPattern3;
        mbs = setTimeMinBefore3;
        SharedPreferences.Editor editor3 = shared3.edit();
        editor3.putString(time3, ts);
        editor3.putString(minb3, mbs);
        editor3.putInt(food3t, fps);
        editor3.putString(nopill3, ps);
        editor3.apply();
        Log.i("Creatas", "Mok");
    }

    public void retrieve1() {
        SharedPreferences sp1 = getSharedPreferences(sharedPfor1, Context.MODE_PRIVATE);
        String rshowtime = sp1.getString(time1, "12:00 PM");
        String rpillsata = sp1.getString(nopill1, "1");
        int rfoo1t = sp1.getInt(food1t, 0);
        Log.i("foodpattern", String.valueOf(foodPattern1));
        if (rfoo1t == 1) {
            setfood1inner();
            Log.i("foodpatternp", String.valueOf(rfoo1t));
        } else if (rfoo1t == 2) {
            setfood2inner();
            Log.i("foodpatternp2", String.valueOf(rfoo1t));

        } else if (rfoo1t == 3) {
            setfood3inner();
            Log.i("foodpatternp3", String.valueOf(rfoo1t));
        }
        String rsetTimeMin = sp1.getString(minb1, "5");

        if (rsetTimeMin.equals("5"))
            spinnerMinBefore.setSelection(0);
        else if (rsetTimeMin.equals("10"))
            spinnerMinBefore.setSelection(1);
        else if (rsetTimeMin.equals("15"))
            spinnerMinBefore.setSelection(2);

        Log.i("showtime", rshowtime);

        if (rshowtime.isEmpty())
            showTime.setText("12:00 PM");
        else
            showTime.setText(rshowtime);
        pillsAtaTime.setText(rpillsata);
    }

    public void retrieve2() {
        SharedPreferences sp2 = getSharedPreferences(sharedPfor2, Context.MODE_PRIVATE);
        String rshowtime = sp2.getString(time2, "12:00 PM");
        String rpillsata = sp2.getString(nopill2, "1");
        int rfoo2t = sp2.getInt(food2t, 0);

        Log.i("foodpattern", String.valueOf(foodPattern1));

        if (rfoo2t == 1) {
            setfood1inner();
            Log.i("foodpatternp", String.valueOf(rfoo2t));

        } else if (rfoo2t == 2) {
            setfood2inner();
            Log.i("foodpatternp2", String.valueOf(rfoo2t));

        } else if (rfoo2t == 3) {
            setfood3inner();
            Log.i("foodpatternp3", String.valueOf(rfoo2t));

        }

        String rsetTimeMin = sp2.getString(minb2, "5");
        if (rsetTimeMin.equals("5"))
            spinnerMinBefore.setSelection(0);
        else if (rsetTimeMin.equals("10"))
            spinnerMinBefore.setSelection(1);
        else if (rsetTimeMin.equals("15"))
            spinnerMinBefore.setSelection(2);

        Log.i("showtime", rshowtime);

        if (rshowtime.isEmpty())
            showTime.setText("12:00 PM");
        else
            showTime.setText(rshowtime);

        pillsAtaTime.setText(rpillsata);
    }

    public void retrieve3() {
        SharedPreferences sp3 = getSharedPreferences(sharedPfor3, Context.MODE_PRIVATE);
        String rshowtime = sp3.getString(time3, "12:00 PM");
        String rpillsata = sp3.getString(nopill3, "1");
        int rfoo2t = sp3.getInt(food3t, 0);

        Log.i("foodpattern", String.valueOf(foodPattern1));

        if (rfoo2t == 1) {
            setfood1inner();
            Log.i("foodpatternp", String.valueOf(rfoo2t));

        } else if (rfoo2t == 2) {
            setfood2inner();
            Log.i("foodpatternp2", String.valueOf(rfoo2t));

        } else if (rfoo2t == 3) {
            setfood3inner();
            Log.i("foodpatternp3", String.valueOf(rfoo2t));

        }

        String rsetTimeMin = sp3.getString(minb3, "5");
        if (rsetTimeMin.equals("5"))
            spinnerMinBefore.setSelection(0);
        else if (rsetTimeMin.equals("10"))
            spinnerMinBefore.setSelection(1);
        else if (rsetTimeMin.equals("15"))
            spinnerMinBefore.setSelection(2);

        Log.i("showtime", rshowtime);

        if (rshowtime.isEmpty())
            showTime.setText("12:00 PM");
        else
            showTime.setText(rshowtime);

        pillsAtaTime.setText(rpillsata);
    }

    private void setFood1() {
        food1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minBefore.setText("min / before eating");
                setfood1inner();
            }
        });
    }

    private void setFood2() {
        food2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minBefore.setText("min / while eating");
                setfood2inner();
            }
        });
    }

    private void setFood3() {
        food3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minBefore.setText("min / after eating");

                setfood3inner();
            }
        });
    }


    private void initSpinnerMinBefore() {
        final String[] items = new String[]{"5", "10", "15"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_day, items);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerMinBefore.setAdapter(adapter);

        spinnerMinBefore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                String items = spinnerMinBefore.getSelectedItem().toString();
                if (pillcheck1) {
                    setTimeMinBefore1 = items;
                    // retrieve1();
                }
                if (pillcheck2) {
                    setTimeMinBefore2 = items;
                    //retrieve2();
                }
                if (pillcheck3) {
                    //retrieve3();
                    setTimeMinBefore3 = items;
                    Log.i("ferdi", items);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }

    public void initSpinner() {
        final String[] items = new String[]{"Every Day", "Every Month"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_lay, items);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                //  items[0] = "One";
                String items = spinner.getSelectedItem().toString();
                if (items.equals("Every Day")) {
                    days.setText("days");
                    perdays.setText("/ per day");
                } else if (items.equals("Every Month")) {
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

    public void initSpinnerDay() {
        final String[] items = new String[]{"7", "1", "2", "3"};
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

    public void setSnackBar() {
        final Snackbar snackbar = Snackbar.make(primelayout, "", Snackbar.LENGTH_SHORT);
        View snackBarView = getLayoutInflater().inflate(R.layout.snackbar_layout, null);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) snackbar.getView().getLayoutParams();
        params.setMargins(12, 12, 12, 12);
        snackbar.getView().setLayoutParams(params);

        snackBarView.findViewById(R.id.undo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbarLayout.addView(snackBarView, 0);
        snackbar.setDuration(800);
        snackbar.show();
    }

    public void initperSpinnerDay() {
        final String[] items = new String[]{"1", "2", "3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_day, items);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        perdaySpinner.setAdapter(adapter);

        perdaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

                String items = perdaySpinner.getSelectedItem().toString();
                pillcheck1 = true;
                pillcheck2 = false;
                pillcheck3 = false;
                if (items.equals("2")) {
                    pill2.setVisibility(View.VISIBLE);
                    pill3.setVisibility(View.GONE);

                    retrieve1();
                    pill1.setBackground(getResources().getDrawable(R.drawable.whiteborder));
                    pill2.setBackground(p2);
                    pill3.setBackground(p3);
                }
                if (items.equals("3")) {
                    pill3.setVisibility(View.VISIBLE);
                    pill2.setVisibility(View.VISIBLE);

                    retrieve1();
                    pill1.setBackground(getResources().getDrawable(R.drawable.whiteborder));
                    pill2.setBackground(p2);
                    pill3.setBackground(p3);
                }
                if (items.equals("1")) {
                    pill2.setVisibility(View.GONE);
                    pill1.setBackground(getResources().getDrawable(R.drawable.whiteborder));
                    pill3.setVisibility(View.GONE);

                    retrieve1();
                    pill2.setBackground(p2);
                    pill3.setBackground(p3);
                }
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

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.datepicker, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);
                String dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();
                String week = DateFormat.format("EEEE", calendar1).toString();
                String cdate = DateFormat.format("d", calendar1).toString();
                if (cdate.length() == 1)
                    cdate = '0' + cdate;
                String name_of_week = "";
                for (int i = 0; i < 3; i++)
                    name_of_week = name_of_week + week.charAt(i);

                c2date.setText(cdate);
                c2weekname.setText(name_of_week);

                Log.i("Grisso", week);
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
                Log.i("ferd", dateText);
                showTime.setText(dateText);
                if (pillcheck1)
                    selectedTime1 = dateText.toString();
                if (pillcheck2)
                    selectedTime2 = dateText.toString();
                if (pillcheck3)
                    selectedTime3 = dateText.toString();

            }
        }, HOUR, MINUTE, is24HourFormat);
        timePickerDialog.show();
    }

    private void setfood1inner() {
        triggerTick = true;
        checkButton.setVisibility(View.VISIBLE);
        foodPattern1 = 1;
        foodPattern2 = 1;
        foodPattern3 = 1;

        Log.i("creditti", String.valueOf(triggerTick));
        foodcheck1 = true;
        foodcheck2 = false;
        foodcheck3 = false;
        food1.setBackgroundColor(0x4000ACC1);
        food2.setBackground((Drawable) food2.getTag());
        food3.setBackground((Drawable) food3.getTag());
        Log.i("Checking for food 1", String.valueOf(foodcheck1));
        Log.i("Checking for food 2", String.valueOf(foodcheck2));
        Log.i("Checking for food 3", String.valueOf(foodcheck3));
    }

    private void setfood2inner() {
        triggerTick = true;
        foodPattern1 = 2;
        foodPattern2 = 2;
        foodPattern3 = 2;
        checkButton.setVisibility(View.VISIBLE);
        food2.setBackgroundColor(0x4000ACC1);
        foodcheck1 = false;
        foodcheck2 = true;
        foodcheck3 = false;
        food1.setBackground((Drawable) food1.getTag());
        food3.setBackground((Drawable) food3.getTag());
        Log.i("creditti", String.valueOf(triggerTick));
        Log.i("Checking for food 1", String.valueOf(foodcheck1));
        Log.i("Checking for food 2", String.valueOf(foodcheck2));
        Log.i("Checking for food 3", String.valueOf(foodcheck3));
    }

    private void setfood3inner() {
        triggerTick = true;
        foodPattern1 = 3;
        foodPattern2 = 3;
        foodPattern3 = 3;
        checkButton.setVisibility(View.VISIBLE);
        Log.i("creditti", String.valueOf(triggerTick));
        foodcheck1 = false;
        foodcheck2 = false;
        foodcheck3 = true;
        food3.setBackgroundColor(0x4000ACC1);
        food1.setBackground((Drawable) food1.getTag());
        food2.setBackground((Drawable) food2.getTag());
        Log.i("Checking for food 1", String.valueOf(foodcheck1));
        Log.i("Checking for food 2", String.valueOf(foodcheck2));
        Log.i("Checking for food 3", String.valueOf(foodcheck3));
    }
//
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("premiun", "DFDfd");

    }

    @Override
    public void finish() {
        SharedPreferences myPrefs = this.getSharedPreferences(sharedPfor1, MODE_PRIVATE);
        myPrefs.edit().remove(sharedPfor1).apply();
        myPrefs.edit().clear().apply();
        myPrefs.edit().apply();
        super.finish();
    }

    public void saveNote() {
        String per = perdaySpinner.getSelectedItem().toString();
        Log.i("Persuade", per);
        int total_pills = Integer.parseInt(per);
        data.putExtra(TOTAL_NUMBER_OF_PILLS, total_pills);
        setResult(RESULT_OK, data);
        finish();
//        SharedPreferences myPrefs = this.getSharedPreferences(sharedPfor1, MODE_PRIVATE);
//        myPrefs.edit().remove(sharedPfor1).apply();
//        myPrefs.edit().clear().apply();
//        myPrefs.edit().apply();
    }
}
