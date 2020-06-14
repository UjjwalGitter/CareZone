package com.ujjwalsingh.carezone.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ujjwalsingh.carezone.Helperclass.LocationHelper;
import com.ujjwalsingh.carezone.R;

import java.util.ArrayList;

 public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {
    ArrayList<LocationHelper> locationHelpersList;

    public LocationAdapter(ArrayList<LocationHelper> locationHelpers) {
        this.locationHelpersList = locationHelpers;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_location_design,parent,false);
        LocationViewHolder locationViewHolder = new LocationViewHolder(view);
        return locationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {

        LocationHelper locationHelper = locationHelpersList.get(position);
        holder.image.setImageResource(locationHelper.getImage());
        holder.name.setText(locationHelper.getTitle());
        holder.descrpition.setText(locationHelper.getDescription());
    }

    @Override
    public int getItemCount() {
        return locationHelpersList.size();
    }

    public static class LocationViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name, descrpition;
        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.photo);
            name = itemView.findViewById(R.id.hospital_name);
            descrpition = itemView.findViewById(R.id.des);
            }
    }
}