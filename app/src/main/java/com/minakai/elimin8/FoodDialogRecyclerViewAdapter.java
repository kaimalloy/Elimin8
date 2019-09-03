package com.minakai.elimin8;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodDialogRecyclerViewAdapter extends RecyclerView.Adapter<FoodDialogRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "FoodDialogRVAdapter";

    private ArrayList<String> enteredFoods;


    public FoodDialogRecyclerViewAdapter() {
        enteredFoods = new ArrayList<>();
    }

    // Called by food dialog when + button is clicked
    public void addItem() {
        Log.d(TAG, "Item Added.");

        enteredFoods.add("");
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_food_dialog, parent, false);
        FoodDialogRecyclerViewAdapter.ViewHolder holder = new FoodDialogRecyclerViewAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.edit_txt.setText(enteredFoods.get(position));
    }

    @Override
    public int getItemCount() {
        return enteredFoods.size();
    }

    public ArrayList<String> getEnteredFoods() {
        return enteredFoods;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        EditText edit_txt;
        ImageButton edit_close_btn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            edit_txt = itemView.findViewById(R.id.edit_txt);
            edit_txt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    enteredFoods.set(getAdapterPosition(), edit_txt.getText().toString());
                }
                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            edit_close_btn = itemView.findViewById(R.id.edit_close_btn);
            edit_close_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "Item Removed.");

                    enteredFoods.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(),enteredFoods.size());
                }
            });
        }

    }
}
