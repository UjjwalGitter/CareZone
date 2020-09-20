package com.ujjwalsingh.carezone.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ujjwalsingh.carezone.AddMedicineActivity;
import com.ujjwalsingh.carezone.R;
import com.ujjwalsingh.carezone.ui.main.NoteViewModel;

public class MedicineFragment extends Fragment {
    private NoteViewModel noteViewModel;
    ImageView addButton;
RecyclerView recyclerView;
RecyclerView.Adapter medicineAdapter;
    public static MedicineFragment newInstance() {
        return new MedicineFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        addButton = view.findViewById(R.id.addButton);
        recyclerView = view.findViewById(R.id.recyle);

    //    recyclerViewStart();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              init();
            }
        });

        return view;
    }
//
//    private void recyclerViewStart() {
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
//        ArrayList<Note> arrayList = new ArrayList<>();
//        arrayList.add(new Note(R.drawable.drug5,"8:00 pm","Paracetamol","15 min.",1));
//        arrayList.add(new Note(R.drawable.drug1,"2:25 am","Omega 3","5 min.",2));
//        arrayList.add(new Note(R.drawable.drug,"1:00 pm","Bipolar Tablet","30 min.",3));
//        arrayList.add(new Note(R.drawable.drug5,"12:30 am","Paracetamol","15 min.",4));
//       // medicineAdapter = new Note(arrayList);
//        recyclerView.setAdapter(medicineAdapter);
//    }

    public void init (){
        Intent intent = new Intent(getActivity(), AddMedicineActivity.class);
        startActivity(intent);
    }


}