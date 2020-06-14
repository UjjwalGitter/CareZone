package com.ujjwalsingh.carezone.Adapter;

import android.provider.ContactsContract;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ujjwalsingh.carezone.Helperclass.MedicineHelper;
import com.ujjwalsingh.carezone.R;

import java.util.ArrayList;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewModel> {

    ArrayList<MedicineHelper> medicineHelperList;

    public MedicineAdapter(ArrayList<MedicineHelper> medicineHelperList) {
        this.medicineHelperList = medicineHelperList;
    }

    @NonNull
    @Override
    public MedicineViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_pills,parent,false);
        MedicineViewModel medicineViewModel = new MedicineViewModel(view);
        return medicineViewModel;
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewModel holder, int position) {
    MedicineHelper medicineHelper = medicineHelperList.get(position);
    holder.photo.setImageResource(medicineHelper.getImage());
    holder.name.setText(medicineHelper.getName());
    holder.time.setText(medicineHelper.getTime());
    holder.duration.setText(medicineHelper.getDuration());

    boolean isExpandable = medicineHelperList.get(position).isExpandable();

    holder.staticLayout.setVisibility(isExpandable ?  View.VISIBLE : View.GONE);

    if (holder.staticLayout.getVisibility()==View.VISIBLE)
        holder.arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
else
        holder.arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);



    }

    @Override
    public int getItemCount() {
        return medicineHelperList.size();
    }

    public class MedicineViewModel extends RecyclerView.ViewHolder{
        ImageView arrow;
        RelativeLayout staticLayout;
        CardView cardView;
        ImageView photo;
        TextView name,time, duration;
        public MedicineViewModel(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo);
            name= itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            duration = itemView.findViewById(R.id.duration);
            arrow = itemView.findViewById(R.id.dropdown);
            staticLayout = itemView.findViewById(R.id.expand);
            cardView  = itemView.findViewById(R.id.cardview);
           arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   MedicineHelper medicineHelper = medicineHelperList.get(getAdapterPosition());
                   medicineHelper.setExpandable(!medicineHelper.isExpandable());

                   Log.i("Medi",String.valueOf(medicineHelper.isExpandable()));

                    if (medicineHelper.isExpandable()){
                        arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    }else {
                        arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    }
                    notifyItemChanged(getAdapterPosition());
                    Log.i("Medi",String.valueOf(medicineHelper.isExpandable()));

                }
            });
        }
    }

}
