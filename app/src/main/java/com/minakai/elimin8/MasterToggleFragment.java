package com.minakai.elimin8;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MasterToggleFragmentListener} interface
 * to handle interaction events.
 * Use the {@link MasterToggleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MasterToggleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MasterToggleFragmentListener mListener;

    public MasterToggleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MasterToggleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MasterToggleFragment newInstance(String param1, String param2) {
        MasterToggleFragment fragment = new MasterToggleFragment();
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
        View view = inflater.inflate(R.layout.fragment_master_toggle, container, false);


        // Set button
//        Button unverified_btn = view.findViewById(R.id.unverified_btn);
//        unverified_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mListener.unverifiedBtnClicked();
//            }
//        });
//
//        // Set button
//        Button verified_btn = view.findViewById(R.id.verified_btn);
//        verified_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mListener.verifiedBtnClicked();
//            }
//        });

        TabLayout master_tab_layout=view.findViewById(R.id.master_tab_layout);
        master_tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() ==0){
                    mListener.unverifiedBtnClicked();
                }
                else if(tab.getPosition() ==1){
                    mListener.verifiedBtnClicked();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onMasterToggleInputSent(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MasterToggleFragmentListener) {
            mListener = (MasterToggleFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MasterToggleFragmentListener");
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
    public interface MasterToggleFragmentListener {
        // TODO: Update argument type and name
        void onMasterToggleInputSent(Uri uri);
        void unverifiedBtnClicked();
        void verifiedBtnClicked();
    }
}
