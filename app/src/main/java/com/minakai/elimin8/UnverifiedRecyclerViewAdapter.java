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

public class UnverifiedRecyclerViewAdapter extends RecyclerView.Adapter<UnverifiedRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "UnverifiedRcyclAdapter";

    private ArrayList<Integer> imgNames;

    private ArrayList<String[][]> mealPredictions;

    public UnverifiedRecyclerViewAdapter(ArrayList<Integer> img, ArrayList<String[][]> meals){
        imgNames = img;
        mealPredictions = meals;
    }

    @NonNull
    @Override
    public UnverifiedRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_unverified, parent, false);
        ViewHolder holder = new ViewHolder(view);;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UnverifiedRecyclerViewAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.item_img.setImageResource(imgNames.get(position));

        holder.item_food_lbl1.setText(mealPredictions.get(position)[0][0]);
        holder.item_food_lbl2.setText(mealPredictions.get(position)[1][0]);
        holder.item_food_lbl3.setText(mealPredictions.get(position)[2][0]);
        holder.item_food_lbl4.setText(mealPredictions.get(position)[3][0]);

        holder.item_count_lbl1.setText(mealPredictions.get(position)[0][1]);
        holder.item_count_lbl2.setText(mealPredictions.get(position)[1][1]);
        holder.item_count_lbl3.setText(mealPredictions.get(position)[2][1]);
        holder.item_count_lbl4.setText(mealPredictions.get(position)[3][1]);

    }

    @Override
    public int getItemCount()  {
        return mealPredictions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView item_img;

        TextView item_food_lbl1;
        TextView item_food_lbl2;
        TextView item_food_lbl3;
        TextView item_food_lbl4;

        TextView item_count_lbl1;
        TextView item_count_lbl2;
        TextView item_count_lbl3;
        TextView item_count_lbl4;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_img = itemView.findViewById(R.id.recycle_item_img);

            item_food_lbl1 = itemView.findViewById(R.id.recycle_item_food_lbl1);
            item_food_lbl2 = itemView.findViewById(R.id.recycle_item_food_lbl2);
            item_food_lbl3 = itemView.findViewById(R.id.recycle_item_food_lbl3);
            item_food_lbl4 = itemView.findViewById(R.id.recycle_item_food_lbl4);

            item_count_lbl1 = itemView.findViewById(R.id.recycle_item_count_lbl1);
            item_count_lbl2 = itemView.findViewById(R.id.recycle_item_count_lbl2);
            item_count_lbl3 = itemView.findViewById(R.id.recycle_item_count_lbl3);
            item_count_lbl4 = itemView.findViewById(R.id.recycle_item_count_lbl4);
        }
    }
}
