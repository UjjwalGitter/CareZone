package com.ujjwalsingh.carezone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ujjwalsingh.carezone.Adapter.MedicineAdapter;
import com.ujjwalsingh.carezone.DataBase.Note;
import com.ujjwalsingh.carezone.ui.main.NoteViewModel;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class FirstActivity extends AppCompatActivity {
    static String profile_name="";
    static String gender="";
    int notification_count;
    boolean firstrun;
    TextView profileName;
    CircleImageView profile_pic;
    TextView c2date;
    TextView c1date;
    TextView c3date;
    TextView c4date;
    TextView c1week;
    TextView c2week;
    TextView c3week;
    TextView c4week;
    public static final String channelName = "Channel Name";
    Map<Integer,Integer> map = new HashMap<>();
    private static final String PREFERENCE_LAST_NOTIF_ID = "PREFERENCE_LAST_NOTIF_ID";
    public int incre = 0;
    public boolean isCheckedDone = false;
    public static final int ADD_NOTE_REQUEST = 1;
    public static NoteViewModel noteViewModel;
    RecyclerView recyclerView;
    ImageView addButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Log.i("Createdw","dsd");

        profileName = findViewById(R.id.profile_name);
        profile_pic = findViewById(R.id.profile_pic);
        profile_name = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("profile_name","Dannie");
        gender = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("gender","male");
        profileName.setText(profile_name);

        profile_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, AboutMe.class);
                startActivity(intent);
            }
        });

        if(gender.equals("Male"))
            profile_pic.setImageResource(R.drawable.male_pic);
        else if(gender.equals("Female"))
            profile_pic.setImageResource(R.drawable.female_pic);

        //createNotification();
        onCreateDialog();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        addButton = findViewById(R.id.addButton);
        recyclerView = findViewById(R.id.recyle);
        c1date = findViewById(R.id.c1date);
        c2date = findViewById(R.id.c2date);
        c3date = findViewById(R.id.c3date);
        c4date = findViewById(R.id.c4date);
        c1week = findViewById(R.id.c1week);
        c3week = findViewById(R.id.c3week);
        c2week = findViewById(R.id.c2week);
        c4week = findViewById(R.id.c4week);

        Calendar yes = Calendar.getInstance();
        yes.add(Calendar.DATE, -1);
        int d = yes.get(Calendar.DAY_OF_MONTH);
        int w = yes.get(Calendar.DAY_OF_WEEK);
        int m = yes.get(Calendar.MONTH);
        int y = yes.get(Calendar.YEAR);
        //String date = day + "/" + (month + 1) + "/" + year;
        c2date.setText(String.valueOf(d));
        if (w == 1)
            c2week.setText("sun");
        else if (w == 2)
            c2week.setText("mon");
        else if (w == 3)
            c2week.setText("tue");
        else if (w == 4)
            c2week.setText("wed");
        else if (w == 5)
            c2week.setText("thu");
        else if (w == 6)
            c2week.setText("fri");
        else if (w == 7)
            c2week.setText("sat");

        Log.i("caled yes", String.valueOf(d));
        Log.i("caled w", String.valueOf(w));
        for (int i = 0; i < 3; i++) {
            Calendar crate = Calendar.getInstance();
            crate.add(Calendar.DATE, i);
            int day = crate.get(Calendar.DAY_OF_MONTH);
            int weekloop = crate.get(Calendar.DAY_OF_WEEK);
            int month = crate.get(Calendar.MONTH);
            int year = crate.get(Calendar.YEAR);
            String date = day + "/" + (month + 1) + "/" + year;
            Log.i("caled", String.valueOf(day));
            Log.i("caled w", String.valueOf(weekloop));
            if (i == 0) {
                c1date.setText(String.valueOf(day));
                if (weekloop == 1)
                    c1week.setText("sun");
                else if (weekloop == 2)
                    c1week.setText("mon");
                else if (weekloop == 3) {
                    c1week.setText("tue");
                }
                else if (weekloop == 4) {
                    c1week.setText("wed");
                }
                else if (weekloop == 5){
                    c1week.setText("thu");
            }
                else if (weekloop == 6)
                    c1week.setText("fri");
                else if (w == 7)
                    c1week.setText("sat");
            }
            if (i == 1) {
                c3date.setText(String.valueOf(day));
                if (weekloop == 1)
                    c3week.setText("sun");
                else if (weekloop == 2)
                    c3week.setText("mon");
                else if (weekloop == 3){
                    c3week.setText("tue");}
                else if (weekloop == 4){
                    c3week.setText("wed");}
                else if (weekloop == 5){
                    c3week.setText("thu");}
                else if (weekloop == 6)
                    c3week.setText("fri");
                else if (weekloop == 7)
                    c3week.setText("sat");
            }
            if (i == 2) {
                c4date.setText(String.valueOf(day));
                if (weekloop == 1)
                    c4week.setText("sun");
                else if (weekloop == 2)
                    c4week.setText("mon");
                else if (weekloop == 3){
                    c4week.setText("tue");}
                else if (weekloop == 4)
                    c4week.setText("wed");
                else if (weekloop == 5)
                    c4week.setText("thu");
                else if (weekloop == 6)
                    c4week.setText("fri");
                else if (weekloop == 7)
                    c4week.setText("sat");
            }
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final MedicineAdapter medicineAdapter = new MedicineAdapter();

        recyclerView.setAdapter(medicineAdapter);
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        try {
            noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
                @Override
                public void onChanged(List<Note> notes) {
                    medicineAdapter.setNotes(notes);
                    try {
                        if (!notes.isEmpty()) {
                            for (int i = 0; i < notes.size(); i++) {
                                Log.i("Debugma", String.valueOf(notes.get(i).getId()) + notes.get(i).getName());
                                String t = notes.get(i).getTime();
                                String pill_name = notes.get(i).getName();
                                int icon = notes.get(i).getImage();
                                int id = notes.get(i).getId();
                                String hr = "";
                                String min = "";
                                String ampm = "";
                                if (t.charAt(1) == ':') {
                                    hr = String.valueOf(t.charAt(0));
                                    min = String.valueOf(Character.toString(t.charAt(2)) + Character.toString(t.charAt(3)));
                                    ampm = String.valueOf(Character.toString(t.charAt(5)) + Character.toString(t.charAt(6)));
                                } else {
                                    hr = String.valueOf(Character.toString(t.charAt(0)) + Character.toString(t.charAt(1)));
                                    min = String.valueOf(Character.toString(t.charAt(3)) + Character.toString(t.charAt(4)));
                                    ampm = String.valueOf(Character.toString(t.charAt(6)) + Character.toString(t.charAt(7)));
                                }

                                int hour = Integer.valueOf(hr);
                                System.out.println(hour);
                                System.out.println(hour + 3);


                                Log.i("sesr", String.valueOf(hr));
                                Log.i("sesr", String.valueOf(min));
                                Log.i("sesr", String.valueOf(ampm));
                                Log.i("Debugmas", String.valueOf(hour + 3));

                                Calendar timer = Calendar.getInstance();
                                timer.set(Calendar.HOUR, Integer.parseInt(hr));
                                timer.set(Calendar.MINUTE, Integer.parseInt(min));
                                timer.set(Calendar.SECOND, 0);
                                if (ampm.equals("AM")) {
                                    timer.set(Calendar.AM_PM, 0);
                                    Log.i("Reverb", "AM");
                                } else if (ampm.equals("PM")) {
                                    timer.set(Calendar.AM_PM, 1);
                                    Log.i("Reverb", "PM");
                                }

                                notification_count = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("notification_count", 0);
                            Log.i("notify",String.valueOf(notification_count));
                                if (notification_count<notes.get(i).getId()) {
                                    startAlarm(timer, getNextNotifId(getApplicationContext()), pill_name, icon, id);
                                    map.put(notes.get(i).getId(), 1);
                                    Log.i("jjjj",pill_name);
                                    Log.i("machien",pill_name+" "+String.valueOf(id));
                                }
                                String finTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(timer.getTime());

                                Log.i("", finTime);
                            }
                        }
                    } catch (Exception e) {

                    }
                    Log.i("Debugm", String.valueOf(notes.size()));
                }
            });
        } catch (Exception e) {

        }
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(isCheckedDone){
//                    addButton.setSpeed(2);
//                    addButton.playAnimation();
//                    isCheckedDone = false;
//                }else{ addButton.setSpeed(2);
//                    addButton.playAnimation();
//                    isCheckedDone = true;
//                }
                Intent intent = new Intent(FirstActivity.this, AddMedicineActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
               // finish();
            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Log.i("Swappes", "Dfd");
                Log.i("Srew", String.valueOf(viewHolder.getAdapterPosition()));
                Note note = medicineAdapter.getNoteAt(viewHolder.getAdapterPosition());
                noteViewModel.delete(medicineAdapter.getNoteAt(viewHolder.getAdapterPosition()));
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {

            int total_number_pills = data.getIntExtra(AddMedicineActivity.TOTAL_NUMBER_OF_PILLS, 0);

            if (total_number_pills == 1) {
                String name1 = data.getStringExtra(AddMedicineActivity.EXTRA_NAME1);
                String time1 = data.getStringExtra(AddMedicineActivity.EXTRA_TIME1);
                String minbefore1 = data.getStringExtra(AddMedicineActivity.EXTRA_MINBEFORE1);
                int foodpattern1 = data.getIntExtra(AddMedicineActivity.EXTRA_FOODPATTERN1, 0);
                int image1 = data.getIntExtra(AddMedicineActivity.EXTRA_NAME1, 0);
                Note note1 = new Note(name1, time1, minbefore1, foodpattern1, image1);
                noteViewModel.insert(note1);
                Log.i("Drigger", name1);

            } else if (total_number_pills == 2) {
                String name1 = data.getStringExtra(AddMedicineActivity.EXTRA_NAME1);
                String time1 = data.getStringExtra(AddMedicineActivity.EXTRA_TIME1);
                String minbefore1 = data.getStringExtra(AddMedicineActivity.EXTRA_MINBEFORE1);
                int foodpattern1 = data.getIntExtra(AddMedicineActivity.EXTRA_FOODPATTERN1, 0);
                int image1 = data.getIntExtra(AddMedicineActivity.EXTRA_NAME1, 0);
                Note note1 = new Note(name1, time1, minbefore1, foodpattern1, image1);
                noteViewModel.insert(note1);

                String name2 = data.getStringExtra(AddMedicineActivity.EXTRA_NAME2);
                String time2 = data.getStringExtra(AddMedicineActivity.EXTRA_TIME2);
                String minbefore2 = data.getStringExtra(AddMedicineActivity.EXTRA_MINBEFORE2);
                int foodpattern2 = data.getIntExtra(AddMedicineActivity.EXTRA_FOODPATTERN2, 0);
                int image2 = data.getIntExtra(AddMedicineActivity.EXTRA_NAME2, 0);
                Note note2 = new Note(name2, time2, minbefore2, foodpattern2, image2);
                noteViewModel.insert(note2);

            } else if (total_number_pills == 3) {

                String name1 = data.getStringExtra(AddMedicineActivity.EXTRA_NAME1);
                String time1 = data.getStringExtra(AddMedicineActivity.EXTRA_TIME1);
                String minbefore1 = data.getStringExtra(AddMedicineActivity.EXTRA_MINBEFORE1);
                int foodpattern1 = data.getIntExtra(AddMedicineActivity.EXTRA_FOODPATTERN1, 0);
                int image1 = data.getIntExtra(AddMedicineActivity.EXTRA_NAME1, 0);
                Note note1 = new Note(name1, time1, minbefore1, foodpattern1, image1);
                noteViewModel.insert(note1);


                String name2 = data.getStringExtra(AddMedicineActivity.EXTRA_NAME2);
                String time2 = data.getStringExtra(AddMedicineActivity.EXTRA_TIME2);
                String minbefore2 = data.getStringExtra(AddMedicineActivity.EXTRA_MINBEFORE2);
                int foodpattern2 = data.getIntExtra(AddMedicineActivity.EXTRA_FOODPATTERN2, 0);
                int image2 = data.getIntExtra(AddMedicineActivity.EXTRA_NAME2, 0);
                Note note2 = new Note(name2, time2, minbefore2, foodpattern2, image2);
                noteViewModel.insert(note2);

                String name3 = data.getStringExtra(AddMedicineActivity.EXTRA_NAME3);
                String time3 = data.getStringExtra(AddMedicineActivity.EXTRA_TIME3);
                String minbefore3 = data.getStringExtra(AddMedicineActivity.EXTRA_MINBEFORE3);
                int foodpattern3 = data.getIntExtra(AddMedicineActivity.EXTRA_FOODPATTERN3, 0);
                int image3 = data.getIntExtra(AddMedicineActivity.EXTRA_NAME3, 0);
                Note note3 = new Note(name3, time3, minbefore3, foodpattern3, image3);
                noteViewModel.insert(note3);
            }
        } else
            Toast.makeText(this, "Note not Created", Toast.LENGTH_SHORT).show();
    }

    private void startAlarm(Calendar c, int rCode, String pill_name, int icon, int id) {
        Log.i("kkkk",pill_name);
        createNotification();

        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .edit()
                .putInt("notification_count",id)
                .apply();

        Log.i("kkkk",pill_name);
        Log.i("stared", c.getTime().toString());
        Log.i("stareder", String.valueOf(rCode));
        Intent intent = new Intent(this, AlertReceiver.class);
        intent.putExtra("pill_name", pill_name);
        intent.putExtra("icon", icon);
        intent.putExtra("id", id);
        intent.putExtra("rcode", rCode);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, rCode, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    private void createNotification() {
        Log.i("pepso", "ssds");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel("channelID", channelName, NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private static int getNextNotifId(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        int id = sharedPreferences.getInt(PREFERENCE_LAST_NOTIF_ID, 0) + 1;
        if (id == Integer.MAX_VALUE) {
            id = 0;
        } // isn't this over kill ??? hahaha!!  ^_^
        sharedPreferences.edit().putInt(PREFERENCE_LAST_NOTIF_ID, id).apply();
        return id;
    }
    @Override
    public void finish() {
        super.finish();
     //   overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    public void openDialog() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final View view = getLayoutInflater().inflate(R.layout.layout_dialog,null);
        String result = "";
        final EditText editText = view.findViewById(R.id.edit_name);
        Button apply = view.findViewById(R.id.apply);
        final RadioGroup radioGroup = view.findViewById(R.id.radioGroup);

        final AlertDialog myAlertdialog;
        alert.setView(view);
        myAlertdialog = alert.create();
        myAlertdialog.show();

        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group,
                                                 int checkedId)
                    {
                        RadioButton
                                radioButton
                                = (RadioButton)group
                                .findViewById(checkedId);
                    }
                });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(FirstActivity.this,
                            "No answer has been selected",
                            Toast.LENGTH_SHORT)
                            .show();
                } else {
                    RadioButton radioButton =radioGroup.findViewById(selectedId);
            gender = radioButton.getText().toString();
                    if(gender.equals("Male"))
                        profile_pic.setImageResource(R.drawable.male_pic);
                    else if(gender.equals("Female"))
                        profile_pic.setImageResource(R.drawable.female_pic);
            profileName.setText(editText.getText().toString());
                    getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                            .edit()
                            .putBoolean("firstrun", false)
                            .apply();
                    getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                            .edit()
                            .putString("profile_name",editText.getText().toString())
                            .apply();
                    getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                            .edit()
                            .putString("gender",gender)
                            .apply();
            Log.i("Prient",profile_name);
                    // Now display the value of selected item
                    // by the Toast message
                    Toast.makeText(FirstActivity.this,
                            radioButton.getText().toString(),
                            Toast.LENGTH_SHORT)
                            .show();

                    myAlertdialog.dismiss();
                }
            }
        });

    }
    public void onCreateDialog(){
        Log.i("camel","Dff");
         firstrun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun", true);
        Log.i("vaios",String.valueOf(firstrun));
        if (firstrun){
            openDialog();
            Log.i("VAoi",String.valueOf(firstrun));
        }
    }


}

