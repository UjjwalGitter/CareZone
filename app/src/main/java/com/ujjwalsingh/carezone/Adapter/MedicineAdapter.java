package com.ujjwalsingh.carezone.Adapter;

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

import com.ujjwalsingh.carezone.DataBase.Note;
import com.ujjwalsingh.carezone.R;

import java.util.ArrayList;
import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {
    private List<Note> notes = new ArrayList<>();
    public Note giveNote;

    int pos = 0;

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_pills, parent, false);
        return new MedicineViewHolder(view);
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        Log.i("Defaultre", String.valueOf(this.notes.size()));
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position) {
        Log.i("Defaultr", String.valueOf(this.notes.size()));
        return notes.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {

        if (pos == position)
            Log.i("Defaulteew", String.valueOf(notes.size()));

        Note note = notes.get(position);
        Log.i("343", "sds");
        Log.i("43", note.toString());
        Log.i("3411", String.valueOf(note.getId()));
        Log.i("34335", String.valueOf(note.getFoodpattern()));

        if (note.getId() % 10 == 1) {
            holder.photo.setImageResource(R.drawable.pill1);
        } else if (note.getId() % 10 == 2) {
            Log.i("Sherkisrt", String.valueOf(note.getImage()));
            holder.photo.setImageResource(R.drawable.pill2);
        } else if (note.getId() % 10 == 3) {
            Log.i("Sherkisrtew", String.valueOf(note.getImage()));
            holder.photo.setImageResource(R.drawable.pill11);
        } else if (note.getId() % 10 == 4) {
            Log.i("Sherkisrtew", String.valueOf(note.getImage()));
            holder.photo.setImageResource(R.drawable.pill4);
        } else if (note.getId() % 10 == 5) {
            Log.i("Sherkisrtew", String.valueOf(note.getImage()));
            holder.photo.setImageResource(R.drawable.pill5);
        } else if (note.getId() % 10 == 6) {
            Log.i("Sherkisrtew", String.valueOf(note.getImage()));
            holder.photo.setImageResource(R.drawable.pill6);
        } else if (note.getId() % 10 == 7) {
            Log.i("Sherkisrtew", String.valueOf(note.getImage()));
            holder.photo.setImageResource(R.drawable.pill7);
        } else if (note.getId() % 10 == 8) {
            Log.i("Sherkisrtew", String.valueOf(note.getImage()));
            holder.photo.setImageResource(R.drawable.pill8);
        }else if (note.getId() % 10 == 9) {
            Log.i("Sherkisrtew", String.valueOf(note.getImage()));
            holder.photo.setImageResource(R.drawable.pill9);
        } else if (note.getId() % 10 == 0) {
            Log.i("Sherkisrtew", String.valueOf(note.getImage()));
            holder.photo.setImageResource(R.drawable.pill10);
        }


        if (note.getFoodpattern() == 1)
            holder.foodp.setImageResource(R.drawable.foodp1);

        if (note.getFoodpattern() == 2)
            holder.foodp.setImageResource(R.drawable.foodp2);

        if (note.getFoodpattern() == 3)
            holder.foodp.setImageResource(R.drawable.foodp3);

        holder.name.setText(note.getName());
        holder.time.setText(note.getTime());
        holder.minbefore.setText(note.getMinbefore());

        boolean isExpandable = notes.get(position).isExpandable();

        holder.staticLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        if (holder.staticLayout.getVisibility() == View.VISIBLE)
            holder.arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
        else
            holder.arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class MedicineViewHolder extends RecyclerView.ViewHolder {

        ImageView arrow, foodp;
        RelativeLayout staticLayout;
        CardView cardView;
        ImageView photo;
        TextView name, time, minbefore;

        public MedicineViewHolder(@NonNull final View itemView) {
            super(itemView);


            photo = itemView.findViewById(R.id.photo);
            foodp = itemView.findViewById(R.id.foodp);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            minbefore = itemView.findViewById(R.id.duration);
            arrow = itemView.findViewById(R.id.dropdown);
            staticLayout = itemView.findViewById(R.id.expand);
            cardView = itemView.findViewById(R.id.cardview);

            if (pos == getAdapterPosition()) {
                Log.i("treggra", String.valueOf(getAdapterPosition()));

                giveNote = notes.get(getAdapterPosition());
            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    giveNote = notes.get(getAdapterPosition());
                    Log.i("treggraTr", String.valueOf(giveNote.getName()));

                }
            });
            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Note note = notes.get(getAdapterPosition());
                    Note note = notes.get(getAdapterPosition());
                    note.setExpandable(!note.isExpandable());

                    Log.i("Medi", String.valueOf(note.isExpandable()));

                    if (note.isExpandable()) {
                        arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                    } else {
                        arrow.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                    }
                    notifyItemChanged(getAdapterPosition());
                    Log.i("Medi", String.valueOf(note.isExpandable()));

                }
            });
        }
    }
}
