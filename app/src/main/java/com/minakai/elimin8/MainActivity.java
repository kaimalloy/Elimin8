package com.minakai.elimin8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity
        implements DashboardFragment.DashboardFragmentListener,
        MasterListFragment.MasterListFragmentListener,
        InsightFragment.InsightFragmentListener,
        CalendarBarFragment.CalendarBarFragmentListener,
        SymptomFragment.SymptomFragmentListener,
        FoodFragment.FoodFragmentListener,
        MasterToggleFragment.MasterToggleFragmentListener,
        UnverifiedFragment.UnverifiedFragmentListener {

    private static final String TAG = "MainActivity";

    // Initialize each fragment
    private DashboardFragment dash_frag;
    private MasterListFragment verified_frag;
    private UnverifiedFragment unverified_frag;
    private InsightFragment insight_frag;
    private CalendarBarFragment cal_bar_frag;
    private SymptomFragment symptom_frag;
    private FoodFragment food_frag;
    private MasterToggleFragment toggle_frag;


    // Fragment State
    private Fragment frag_state;

    // Flare Up Object
    private FlareUp flareUp;

    // Python backend Object
    PyObject pyMatchGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // new flare up object
        flareUp = new FlareUp();

        // Initialize Python
        Python py = Python.getInstance();
        pyMatchGenerator = py.getModule("PyBackend").callAttr("PyBackend");


        // Python test
//        callPython();


        // turn off the default window title
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        dash_frag = new DashboardFragment();
        unverified_frag = new UnverifiedFragment();
        verified_frag = new MasterListFragment();
        insight_frag = new InsightFragment();
        cal_bar_frag = new CalendarBarFragment();
        symptom_frag = new SymptomFragment();
        food_frag = new FoodFragment();
        toggle_frag = new MasterToggleFragment();


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, dash_frag)
                .replace(R.id.cal_fragment_container, cal_bar_frag)
                .commit();

        BottomNavigationView nav = findViewById(R.id.nav);
        nav.setItemIconTintList(null); // remove unnecessary color
        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        // Make Calendar visible
                        findViewById(R.id.cal_fragment_container).setVisibility(View.VISIBLE);

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, dash_frag)
                                .replace(R.id.cal_fragment_container, cal_bar_frag)
                                .commit();
                        break;
                    case R.id.nav_master_list:
                        // Make Toggle visible
                        findViewById(R.id.cal_fragment_container).setVisibility(View.VISIBLE);

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, verified_frag)
                                .replace(R.id.cal_fragment_container, toggle_frag)
                                .commit();
                        break;
                    case R.id.nav_insight:
                        // Make Calendar invisible
                        findViewById(R.id.cal_fragment_container).setVisibility(View.GONE);

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, insight_frag)
                                .commit();
                        break;
                }
                return true;
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
                    flareUp.setSymptoms(((SymptomFragment) frag_state).getSymptoms());

                    frag_state = food_frag;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, food_frag)
                            .commit();
                }
                else if (frag_state instanceof FoodFragment){
                    // Set the foods for the flare up
                    flareUp.setFoods(((FoodFragment) frag_state).getMeals());

                    frag_state = unverified_frag;

                    // Make Calendar invisible
                    findViewById(R.id.cal_fragment_container).setVisibility(View.VISIBLE);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, unverified_frag)
                            .replace(R.id.cal_fragment_container, toggle_frag)
                            .commit();

                    // print the combinations
                    callPython();

                    //clear the meals in the food fragment and the flare up
                    food_frag.resetMeals();
                    flareUp.reset();
                }
                
            }
        });

    }



    // call sample Python Output
    public void callPython(){

//        // input data
//        String[][] arr = {{"Bumpy", "8/1/2019 2pm", "A", "B", "C"},
//                {"Bumpy", "8/5/2019 6pm", "A", "C", "D"},
//                {"Bumpy", "8/12/2019 4pm", "A", "C"},
//                {"Scaly", "8/14/2019 9am", "C", "B", "D"},
//                {"Scaly", "8/20/2019 12pm", "A", "B", "D"},
//                {"Scaly", "8/25/2019 3pm", "A", "B"}};

        String pyText = pyMatchGenerator.callAttr("get_prediction", flareUp.getSymptoms().iterator().next(), flareUp.getTimestamp(), flareUp.getFoods().toArray()).toString();
        pyMatchGenerator.callAttr("clear_output_str");
        Log.d("Python", pyText);
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
        frag_state = verified_frag;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, verified_frag)
                .commit();
    }

    @Override
    public void onUnverifiedInputSent(Uri uri) {

    }


    // Flare Up class to represent
    private class FlareUp {

        private static final String TAG = "FlareUp";

        HashSet<String> symptoms = new HashSet<>();
        ArrayList<String> foods = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        public FlareUp() {
        }

        public void setSymptoms(HashSet<String> symptoms) {
            Log.d(TAG, "Symptoms Entered: " + symptoms.toString());
            this.symptoms = symptoms;
        }

        public HashSet<String> getSymptoms() {
            return symptoms;
        }

        public void setFoods(ArrayList<ArrayList<String>> meals) {
            Log.d(TAG, "Meals Entered: " + meals.toString());

            // flatten the foods into one
            for (ArrayList<String> m : meals){
                this.foods.addAll(m);
            }
        }

        public ArrayList<String> getFoods() {
            return foods;
        }

        public String getTimestamp() {
            return calendar.getTime().toString();
        }

        public void reset() {
            Log.d(TAG, "Flare up reset.");
            symptoms = new HashSet<>();
            foods = new ArrayList<>();
        }

    }
}
