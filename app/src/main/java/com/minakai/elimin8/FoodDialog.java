package com.minakai.elimin8;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodDialog extends Dialog implements View.OnClickListener {

    private static final String TAG = "FoodDialog";

    Button add_btn;
    Button cancel_btn;
    Button save_btn;

    // Recycler View Adapter used to add items dynamically
    FoodDialogRecyclerViewAdapter rvAdapter;

    // Final food input values that are saved
    ArrayList<String> enteredFoods;

    public FoodDialog(Context context) {
        super(context);

        this.setCanceledOnTouchOutside(false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_food);

        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(this);

        cancel_btn = findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(this);

        save_btn = findViewById(R.id.save_btn);
        save_btn.setOnClickListener(this);

        // Set up recycler view
        final RecyclerView recyclerView = findViewById(R.id.food_item_lyt);
        rvAdapter = new FoodDialogRecyclerViewAdapter();
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Add one Food Input Item to begin with
        rvAdapter.addItem();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.add_btn:
                rvAdapter.addItem();
                break;
            case R.id.cancel_btn:
                dismiss();
                break;
            case R.id.save_btn:
                enteredFoods = rvAdapter.getEnteredFoods();
                Log.d(TAG, "Saved Data:" + enteredFoods.toString());

//                dismiss();
                break;
        }
    }
}