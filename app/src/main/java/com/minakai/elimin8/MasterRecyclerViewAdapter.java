package com.minakai.elimin8;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MasterRecyclerViewAdapter extends RecyclerView.Adapter<MasterRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MasterRecyclerViewAdapt";

    private ArrayList<Integer> imgNames;
    private ArrayList<String> foodNames;

    public MasterRecyclerViewAdapter(ArrayList<Integer> img, ArrayList<String> food){
        imgNames = img;
        foodNames = food;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_master, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.item_img.setImageResource(imgNames.get(position));
        holder.item_txt.setText(foodNames.get(position));
    }

    @Override
    public int getItemCount() {
        return foodNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView item_img;
        TextView item_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_img = itemView.findViewById(R.id.recycle_item_img);
            item_txt = itemView.findViewById(R.id.recycle_item_food_lbl);
        }
    }
}
