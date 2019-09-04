package com.minakai.elimin8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements DashboardFragment.DashboardFragmentListener,
        MasterListFragment.MasterListFragmentListener,
        InsightFragment.InsightFragmentListener,
        CalendarBarFragment.CalendarBarFragmentListener,
        SymptomFragment.SymptomFragmentListener,
        FoodFragment.FoodFragmentListener,
        MasterToggleFragment.MasterToggleFragmentListener,
        UnverifiedFragment.UnverifiedFragmentListener {

    // Initialize each fragment
    private DashboardFragment dash_frag;
    private MasterListFragment master_frag;
    private UnverifiedFragment unverified_frag;
    private InsightFragment insight_frag;
    private CalendarBarFragment cal_bar_frag;
    private SymptomFragment symptom_frag;
    private FoodFragment food_frag;
    private MasterToggleFragment toggle_frag;


    // Fragment State
    private Fragment frag_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Test to call Python
        callPython();


        // turn off the default window title
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        dash_frag = new DashboardFragment();
        master_frag = new MasterListFragment();
        unverified_frag = new UnverifiedFragment();
        insight_frag = new InsightFragment();
        cal_bar_frag = new CalendarBarFragment();
        symptom_frag = new SymptomFragment();
        food_frag = new FoodFragment();
        toggle_frag = new MasterToggleFragment();


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, dash_frag)
                .replace(R.id.cal_fragment_container, cal_bar_frag)
                .commit();


        ImageButton home_btn = (ImageButton) findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Make Calendar visible
                findViewById(R.id.cal_fragment_container).setVisibility(View.VISIBLE);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, dash_frag)
                        .replace(R.id.cal_fragment_container, cal_bar_frag)
                        .commit();
            }
        });

        ImageButton master_lst_btn = (ImageButton) findViewById(R.id.master_lst_btn);
        master_lst_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Make Toggle visible
                findViewById(R.id.cal_fragment_container).setVisibility(View.VISIBLE);


                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, master_frag)
                        .replace(R.id.cal_fragment_container, toggle_frag)
                        .commit();
            }
        });


        ImageButton insight_btn = (ImageButton) findViewById(R.id.insight_btn);
        insight_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Make Calendar invisible
                findViewById(R.id.cal_fragment_container).setVisibility(View.GONE);


                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, insight_frag)
                        .commit();
            }
        });

        //Back button listener
        Button back_btn = (Button) findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (frag_state instanceof SymptomFragment)
                {
                    frag_state = dash_frag;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, dash_frag)
                            .commit();
                }
                else if (frag_state instanceof FoodFragment){
                    frag_state = symptom_frag;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, symptom_frag)
                            .commit();
                }

            }
        });


        // Decide which fragment to show
        Button next_btn = (Button) findViewById(R.id.next_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (frag_state instanceof SymptomFragment){
                    frag_state = food_frag;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, food_frag)
                            .commit();
                }
                else if (frag_state instanceof FoodFragment){
                    frag_state = unverified_frag;

                    // Make Calendar invisible
                    findViewById(R.id.cal_fragment_container).setVisibility(View.VISIBLE);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, unverified_frag)
                            .replace(R.id.cal_fragment_container, toggle_frag)
                            .commit();
                }
                
            }
        });

    }


    // call sample Python Output
    public void callPython(){
        Python py = Python.getInstance();

        // input data
        String[][] arr = {{"Bumpy", "8/1/2019 2pm", "A", "B", "C"},
                {"Bumpy", "8/5/2019 6pm", "A", "C", "D"},
                {"Bumpy", "8/12/2019 4pm", "A", "C"},
                {"Scaly", "8/14/2019 9am", "C", "B", "D"},
                {"Scaly", "8/20/2019 12pm", "A", "B", "D"},
                {"Scaly", "8/25/2019 3pm", "A", "B"}};

        PyObject matchingClass = py.getModule("MatchingAlgorithm").callAttr("MatchingAlgorithm");
        String pyText = matchingClass.callAttr("run", (Object)arr).toString();
        Log.d("PYTHON", pyText);
    }


    // Fragment Listener classes. Must implement to run.
    @Override
    public void onDashboardInputSent(Uri uri) {
//        Log.d("Fragment Pressed:", "Dashboard");
    }

    @Override
    public void enterDataBtnClicked() {
        frag_state = symptom_frag;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, symptom_frag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public void onMasterListInputSent(Uri uri) {

    }

    @Override
    public void onInsightInputSent(Uri uri) {

    }

    @Override
    public void onCalendarBarInputSent(Uri uri) {

    }

    @Override
    public void onSymptomInputSent(Uri uri) {

    }

    @Override
    public void onFoodInputSent(Uri uri) {

    }

    @Override
    public void onMasterToggleInputSent(Uri uri) {

    }

    @Override
    public void unverifiedBtnClicked() {
        frag_state = unverified_frag;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, unverified_frag)
                .commit();
    }

    @Override
    public void verifiedBtnClicked() {
        frag_state = symptom_frag;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, master_frag)
                .commit();
    }

    @Override
    public void onUnverifiedInputSent(Uri uri) {

    }
}
