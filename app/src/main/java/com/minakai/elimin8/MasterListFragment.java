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
 * {@link MasterListFragmentListener} interface
 * to handle interaction events.
 * Use the {@link MasterListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MasterListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    // Arraylist for images and text
    ArrayList<Integer> imgNames;
    ArrayList<String> foodNames;


    private MasterListFragmentListener mListener;

    public MasterListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MasterListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MasterListFragment newInstance(String param1, String param2) {
        MasterListFragment fragment = new MasterListFragment();
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
        View view = inflater.inflate(R.layout.fragment_master_list, container, false);


        // Set up recycler view
        populateSampleDataMasterList();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_lyt);
        MasterRecyclerViewAdapter adapter = new MasterRecyclerViewAdapter(imgNames, foodNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));

        return view;
    }


    // populate sample data for master list
    private void populateSampleDataMasterList(){
        imgNames = new ArrayList<>();
        foodNames = new ArrayList<>();

        imgNames.add(R.drawable.bumpy);
        foodNames.add("Apples");

        imgNames.add(R.drawable.scaly);
        foodNames.add("Cookies");

        imgNames.add(R.drawable.itchy);
        foodNames.add("Watermelon");

        imgNames.add(R.drawable.redness);
        foodNames.add("Potatoes");

        imgNames.add(R.drawable.redness);
        foodNames.add("Corn");

        imgNames.add(R.drawable.scaly);
        foodNames.add("Peanuts");

        imgNames.add(R.drawable.redness);
        foodNames.add("Brownies");

        imgNames.add(R.drawable.bumpy);
        foodNames.add("White Bread");

        imgNames.add(R.drawable.itchy);
        foodNames.add("Tacos");

        imgNames.add(R.drawable.scaly);
        foodNames.add("Guavas");
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onMasterListInputSent(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MasterListFragmentListener) {
            mListener = (MasterListFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement DashboardFragmentListener");
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
    public interface MasterListFragmentListener {
        // TODO: Update argument type and name
        void onMasterListInputSent(Uri uri);
    }
}
