package com.ujjwalsingh.carezone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ujjwalsingh.carezone.Adapter.LocationAdapter;
import com.ujjwalsingh.carezone.Adapter.MedicineAdapter;
import com.ujjwalsingh.carezone.Adapter.MostAdapter;
import com.ujjwalsingh.carezone.Helperclass.LocationHelper;
import com.ujjwalsingh.carezone.Helperclass.MostHelper;
import com.ujjwalsingh.carezone.ui.main.MainViewModel;
import com.ujjwalsingh.carezone.ui.main.MedicineFragment;

import java.util.ArrayList;

public class DashBoard extends AppCompatActivity {
    MainViewModel mainViewModel;

RecyclerView recyclerView, mostRecycler;
RecyclerView.Adapter adapter;
RecyclerView.Adapter mostAdapter;
RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_dash_board);
        recyclerView = findViewById(R.id.recycleLocations);
        recyclerViewCreator();
        mostRecycler = findViewById(R.id.recMost);
        relativeLayout = findViewById(R.id.medicne_layout);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        showFragment();
            }
        });

        mostRecyCreator();



    }

    private void mostRecyCreator() {

        mostRecycler.setHasFixedSize(true);
        mostRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        ArrayList<MostHelper> arrayList = new ArrayList<>();

        arrayList.add(new MostHelper(R.drawable.donald,"Mc Donnald's","The best answer, as ha1ogen says, is to make a custom drawable. Start with the 9-patch that is used for normal EditText fields. Modify it to strip out the underbar and other graphics you don't want. With this, your modified EditText will have the same margins and overall appearance as normal EditText fields. If you simply set the background to null, it will lose the margins"));

        arrayList.add(new MostHelper(R.drawable.donald,"City Texas","The best answer, as ha1ogen says, is to make a custom drawable. Start with the 9-patch that is used for normal EditText fields. Modify it to strip out the underbar and other graphics you don't want. With this, your modified EditText will have the same margins and overall appearance as normal EditText fields. If you simply set the background to null, it will lose the margins"));

        arrayList.add(new MostHelper(R.drawable.donald,"Mountain View","The best answer, as ha1ogen says, is to make a custom drawable. Start with the 9-patch that is used for normal EditText fields. Modify it to strip out the underbar and other graphics you don't want. With this, your modified EditText will have the same margins and overall appearance as normal EditText fields. If you simply set the background to null, it will lose the margins"));

        arrayList.add(new MostHelper(R.drawable.donald,"OTPE","The best answer, as ha1ogen says, is to make a custom drawable. Start with the 9-patch that is used for normal EditText fields. Modify it to strip out the underbar and other graphics you don't want. With this, your modified EditText will have the same margins and overall appearance as normal EditText fields. If you simply set the background to null, it will lose the margins"));
        mostAdapter = new MostAdapter(arrayList);
        mostRecycler.setAdapter(mostAdapter);
    }

    private void recyclerViewCreator() {
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<LocationHelper> arrayList = new ArrayList<>();
        arrayList.add(new LocationHelper(R.drawable.donald,"Mc Donnald's","The best answer, as ha1ogen says, is to make a custom drawable. Start with the 9-patch that is used for normal EditText fields. Modify it to strip out the underbar and other graphics you don't want. With this, your modified EditText will have the same margins and overall appearance as normal EditText fields. If you simply set the background to null, it will lose the margins"));

        arrayList.add(new LocationHelper(R.drawable.donald,"City Texas","The best answer, as ha1ogen says, is to make a custom drawable. Start with the 9-patch that is used for normal EditText fields. Modify it to strip out the underbar and other graphics you don't want. With this, your modified EditText will have the same margins and overall appearance as normal EditText fields. If you simply set the background to null, it will lose the margins"));

        arrayList.add(new LocationHelper(R.drawable.donald,"Mountain View","The best answer, as ha1ogen says, is to make a custom drawable. Start with the 9-patch that is used for normal EditText fields. Modify it to strip out the underbar and other graphics you don't want. With this, your modified EditText will have the same margins and overall appearance as normal EditText fields. If you simply set the background to null, it will lose the margins"));

        arrayList.add(new LocationHelper(R.drawable.donald,"OTPE","We have  jfk n, nfkn ,fdn,knmng g g g g g g df g fg fd  d  gh g "));

    adapter = new LocationAdapter(arrayList);
    recyclerView.setAdapter(adapter);

    }
    public void showFragment(){
        MedicineFragment h = new MedicineFragment();
                getSupportFragmentManager().
                        beginTransaction().
                        add(R.id.frameLayout,h).commit();

//        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
//        mainViewModel.getText().getValue().
    }
}
