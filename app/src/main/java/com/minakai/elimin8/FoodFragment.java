package com.minakai.elimin8;

import android.content.Context;
import android.content.Intent;
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
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FoodFragmentListener} interface
 * to handle interaction events.
 * Use the {@link FoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodFragment extends Fragment {

    private static final String TAG = "FoodFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    // Arraylist for the foods in each card
    ArrayList<ArrayList<String>> foodNames;

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
        populateSampleData();
        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view_lyt);
        recyclerView.setVisibility(View.GONE);
        FoodRecyclerViewAdapter adapter = new FoodRecyclerViewAdapter(getContext(), foodNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));


        // Temporarily set info button as meals show button
        Button info_btn = view.findViewById(R.id.info_btn);
        final LinearLayout info_lbl = view.findViewById(R.id.info_lbl);
        info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                info_lbl.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

        // Listener for FAB button
        FloatingActionButton fab_btn = view.findViewById(R.id.fab);
        fab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), FoodPopupActivity.class));
            }
        });

        return view;
    }


    // populate sample data for food input
    private void populateSampleData() {
        foodNames = new ArrayList<>();

        foodNames.add(new ArrayList<>(Arrays.asList("Apples", "Lasagna", "Green Peas")));
        foodNames.add(new ArrayList<>(Arrays.asList("Milk", "Coco Puffs")));
        foodNames.add(new ArrayList<>(Arrays.asList("Fried Chicken", "Grapefruit", "Cookie", "Tomatoes")));
        foodNames.add(new ArrayList<>(Arrays.asList("Spaghetti and Meatballs")));
        foodNames.add(new ArrayList<>(Arrays.asList("Cornbread", "Orange Juice", "Spinach Salad")));
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
