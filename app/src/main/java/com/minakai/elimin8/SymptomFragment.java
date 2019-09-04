package com.minakai.elimin8;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.HashSet;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SymptomFragmentListener} interface
 * to handle interaction events.
 * Use the {@link SymptomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SymptomFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private final String itchy_str = "Itchy";
    private final String bumpy_str = "Bumpy";
    private final String redness_str = "Redness";
    private final String scaly_str = "Scaly";

    private HashSet<String> symptoms = new HashSet<>();

    private SymptomFragmentListener mListener;

    public SymptomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SymptomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SymptomFragment newInstance(String param1, String param2) {
        SymptomFragment fragment = new SymptomFragment();
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
        View view = inflater.inflate(R.layout.fragment_symptom, container, false);

        CheckBox itchy_btn = view.findViewById(R.id.itchy_btn);
        itchy_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    symptoms.add(itchy_str);
                }
                else{
                    symptoms.remove(itchy_str);
                }
            }
        });

        CheckBox bumpy_btn = view.findViewById(R.id.bumpy_btn);
        bumpy_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    symptoms.add(bumpy_str);
                }
                else{
                    symptoms.remove(bumpy_str);
                }
            }
        });

        CheckBox redness_btn = view.findViewById(R.id.redness_btn);
        redness_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    symptoms.add(redness_str);
                }
                else{
                    symptoms.remove(redness_str);
                }
            }
        });

        CheckBox scaly_btn = view.findViewById(R.id.scaly_btn);
        scaly_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    symptoms.add(scaly_str);
                }
                else{
                    symptoms.remove(scaly_str);
                }
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSymptomInputSent(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SymptomFragmentListener) {
            mListener = (SymptomFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SymptomFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public HashSet<String> getSymptoms() {
        return symptoms;
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
    public interface SymptomFragmentListener {
        // TODO: Update argument type and name
        void onSymptomInputSent(Uri uri);
    }
}
