package com.minakai.elimin8;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FoodFragmentListener} interface
 * to handle interaction events.
 * Use the {@link FoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodFragment extends Fragment implements FoodDialogFragment.FoodDialogListener {

    private static final String TAG = "FoodFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private LinearLayout info_lbl;
    private RecyclerView recyclerView;
    private FoodRecyclerViewAdapter rvAdapter;


    // Arraylist for the foods in each card
    private ArrayList<ArrayList<String>> meals = new ArrayList<>();

    private FoodFragmentListener mListener;

    public FoodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodFragment newInstance(String param1, String param2) {
        FoodFragment fragment = new FoodFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food, container, false);

        // Set up recycler view
//        populateSampleData();
        recyclerView = view.findViewById(R.id.recycler_view_lyt);
        recyclerView.setVisibility(View.GONE);
        rvAdapter = new FoodRecyclerViewAdapter(getContext(), meals);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));


        // When info BTN is pressed
        Button info_btn = view.findViewById(R.id.info_btn);
        info_lbl = view.findViewById(R.id.info_lbl);
        info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getContext(), "Info BTN pressed", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        // Listener for FAB button
        FloatingActionButton fab_btn = view.findViewById(R.id.fab);
        fab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show the dialog
                FoodDialogFragment dialog = new FoodDialogFragment();
                dialog.setTargetFragment(FoodFragment.this, 1);
                dialog.show(getFragmentManager(), "FoodDialogFragment");
            }
        });

        return view;
    }


    // populate sample data for food input
    private void populateSampleData() {
        meals = new ArrayList<>();

        meals.add(new ArrayList<>(Arrays.asList("Apples", "Lasagna", "Green Peas")));
        meals.add(new ArrayList<>(Arrays.asList("Milk", "Coco Puffs")));
        meals.add(new ArrayList<>(Arrays.asList("Fried Chicken", "Grapefruit", "Cookie", "Tomatoes")));
        meals.add(new ArrayList<>(Arrays.asList("Spaghetti and Meatballs")));
        meals.add(new ArrayList<>(Arrays.asList("Cornbread", "Orange Juice", "Spinach Salad")));
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFoodInputSent(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FoodFragmentListener) {
            mListener = (FoodFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FoodFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    // method implemented for FoodDialogFragment
    @Override
    public void getFoodsFromDialog(ArrayList<String> foods) {
        meals.add(foods);
        rvAdapter.notifyDataSetChanged();

        info_lbl.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    public ArrayList<ArrayList<String>> getMeals() {
        return meals;
    }

    // clear the meals for the next flare up entry
    public void resetMeals() {
        meals = new ArrayList<>();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface FoodFragmentListener {
        // TODO: Update argument type and name
        void onFoodInputSent(Uri uri);
    }
}
