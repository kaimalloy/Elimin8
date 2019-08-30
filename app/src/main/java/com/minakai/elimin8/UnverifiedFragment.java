package com.minakai.elimin8;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UnverifiedFragmentListener} interface
 * to handle interaction events.
 * Use the {@link UnverifiedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UnverifiedFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Arraylist for images and text
    ArrayList<Integer> imgNames;
    ArrayList<String[][]> mealPredictions;

    private UnverifiedFragmentListener mListener;

    public UnverifiedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UnverifiedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UnverifiedFragment newInstance(String param1, String param2) {
        UnverifiedFragment fragment = new UnverifiedFragment();
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
        View view = inflater.inflate(R.layout.fragment_unverified, container, false);

        // Set up recycler view
        populateSampleData();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_lyt);
        UnverifiedRecyclerViewAdapter adapter = new UnverifiedRecyclerViewAdapter(imgNames, mealPredictions);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));

        return view;
    }

    // populate sample data for master list
    private void populateSampleData() {
        imgNames = new ArrayList<>();
        mealPredictions = new ArrayList<String[][]>();

        imgNames.add(R.drawable.bumpy);
        String[][] meal1 = {{"Bananas", "1"},
                {"Spaghetti and Meatballs", "5"},
                {"Bananas and Oranges", "3"},
                {"Asparagus", "2"}};
        mealPredictions.add(meal1);

        imgNames.add(R.drawable.scaly);
        String[][] meal2 = {{"Bananas", "1"},
                {"Spaghetti and Meatballs", "5"},
                {"Bananas and Oranges", "3"},
                {"Asparagus", "2"}};
        mealPredictions.add(meal2);

        imgNames.add(R.drawable.redness);
        String[][] meal3 = {{"Bananas", "1"},
                {"Spaghetti and Meatballs", "5"},
                {"Bananas and Oranges", "3"},
                {"Asparagus", "2"}};
        mealPredictions.add(meal3);

        imgNames.add(R.drawable.itchy);
        String[][] meal4 = {{"Bananas", "1"},
                {"Spaghetti and Meatballs", "5"},
                {"Bananas and Oranges", "3"},
                {"Asparagus", "2"}};
        mealPredictions.add(meal4);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onUnverifiedInputSent(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof UnverifiedFragmentListener) {
            mListener = (UnverifiedFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement UnverifiedFragmentListener");
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
    public interface UnverifiedFragmentListener {
        // TODO: Update argument type and name
        void onUnverifiedInputSent(Uri uri);
    }
}
