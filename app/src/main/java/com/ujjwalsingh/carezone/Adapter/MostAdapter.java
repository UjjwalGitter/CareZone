package com.ujjwalsingh.carezone.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ujjwalsingh.carezone.Helperclass.MostHelper;
import com.ujjwalsingh.carezone.R;

import java.util.ArrayList;

public class MostAdapter extends RecyclerView.Adapter<MostAdapter.MostViewHolder> {
    ArrayList<MostHelper> mostHelperList;

    public MostAdapter(ArrayList<MostHelper> mostHelperList) {
        this.mostHelperList = mostHelperList;
    }

    @NonNull
    @Override
    public MostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed,parent,false);
        MostViewHolder mostViewHolder = new MostViewHolder(view);
        return mostViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MostViewHolder holder, int position) {
        MostHelper mostHelper = mostHelperList.get(position);
        holder.image.setImageResource(mostHelper.getImage());
        holder.name.setText(mostHelper.getTitle());
        holder.des.setText(mostHelper.getDescription());
    }

    @Override
    public int getItemCount() {
        return mostHelperList.size();
    }

    public static class MostViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name, des;

        public MostViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.photo);
            name = itemView.findViewById(R.id.name);
            des = itemView.findViewById(R.id.des);
        }
    }
}
