package com.minakai.elimin8;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodRecyclerViewAdapter extends RecyclerView.Adapter<FoodRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "FoodRecyclerViewAdapter";

    private Context mContext;
    private ArrayList<ArrayList<String>> foodNames;


    public FoodRecyclerViewAdapter(Context context, ArrayList<ArrayList<String>> fNames){
        mContext = context;
        foodNames = fNames;
    }

    private void addTextViews(LinearLayout lyt, int pos) {
        // Remove all current text views first
        lyt.removeAllViews();

        // Add new TextViews
        for (String food: foodNames.get(pos)){
            TextView textView = new TextView(mContext);
            textView.setText(food);
            lyt.addView(textView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_food, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.mealNumberLabel.setText("Meal " + (position + 1));

        // My method to add all the TextViews to the layout
        addTextViews(holder.mealLayout, position);
    }

    @Override
    public int getItemCount() {
        return foodNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mealNumberLabel;
        LinearLayout mealLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mealNumberLabel = itemView.findViewById(R.id.meal_num_lbl);
            mealLayout = itemView.findViewById(R.id.meal_lyt);
        }
    }
}
