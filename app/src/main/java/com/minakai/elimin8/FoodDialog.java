package com.minakai.elimin8;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FoodDialog extends Dialog implements View.OnClickListener {

    private static final String TAG = "FoodDialog";

    Button add_btn;
    Button cancel_btn;
    Button save_btn;

    LinearLayout food_item_lyt;
    ArrayList<Integer> editTexts;

    public FoodDialog(Context context) {
        super(context);

        this.setCanceledOnTouchOutside(false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_food);

        food_item_lyt = findViewById(R.id.food_item_lyt);

        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(this);

        cancel_btn = findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(this);

        save_btn = findViewById(R.id.save_btn);
        save_btn.setOnClickListener(this);

        // Add one Food Input Item to begin with
        addFoodInputItem();

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.add_btn:
                addFoodInputItem();
                break;
            case R.id.cancel_btn:
                dismiss();
                break;
            case R.id.save_btn:
                dismiss();
                break;
        }
    }

    private void addFoodInputItem(){
        View child = getLayoutInflater().inflate(R.layout.input_item_food_dialog, null);
        food_item_lyt.addView(child);
    }

//    // class to store the data from each Edit Text
//    public class FoodItem extends Activity {
//
//        String foodName;
//
//        public FoodItem(Context context){
//            super(context);
//        }
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.dialog_food);
//
//
//    }
}
