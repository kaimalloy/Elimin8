package com.minakai.elimin8;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodDialogFragment extends DialogFragment {

    private static final String TAG = "FoodDialogFragment";

    Button add_btn;
    Button cancel_btn;
    Button save_btn;

    private FoodDialogListener mListener;

    // Recycler View Adapter used to add items dynamically
    private FoodDialogRecyclerViewAdapter rvAdapter;

    // Final food input values that are saved
    private ArrayList<String> enteredFoods;

    public FoodDialogFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dialog_fragment_food, container, false);

//        view.setCanceledOnTouchOutside(false);

        // Set up recycler view
        final RecyclerView recyclerView = view.findViewById(R.id.food_item_lyt);
        rvAdapter = new FoodDialogRecyclerViewAdapter();
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Add one Food Input Item to begin with
        rvAdapter.addItem();


        add_btn = view.findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvAdapter.addItem();
            }
        });

        cancel_btn = view.findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        save_btn = view.findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enteredFoods = rvAdapter.getEnteredFoods();
                mListener.getFoodsFromDialog(enteredFoods);
                Log.d(TAG, "Saved Data:" + enteredFoods.toString());

                dismiss();
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (FoodDialogListener) getTargetFragment();
        }
        catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage());
        }
    }

    public interface FoodDialogListener {
        void getFoodsFromDialog(ArrayList<String> foods);
    }
}