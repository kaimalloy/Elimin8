package com.minakai.elimin8;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements DashboardFragment.DashboardFragmentListener,
        MasterListFragment.MasterListFragmentListener,
        InsightFragment.InsightFragmentListener,
        CalendarBarFragment.CalendarBarFragmentListener,
        SymptomFragment.SymptomFragmentListener,
        FoodFragment.FoodFragmentListener {
    private DashboardFragment dash_frag;
    private MasterListFragment master_frag;
    private InsightFragment insight_frag;
    private CalendarBarFragment cal_bar_frag;
    private SymptomFragment symptom_frag;
    private FoodFragment food_frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dash_frag = new DashboardFragment();
        master_frag = new MasterListFragment();
        insight_frag = new InsightFragment();
        cal_bar_frag = new CalendarBarFragment();
        symptom_frag = new SymptomFragment();
        food_frag = new FoodFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, symptom_frag)
                .replace(R.id.cal_fragment_container, cal_bar_frag)
                .commit();


        Button home_btn = (Button) findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, dash_frag)
                        .commit();
            }
        });

        Button master_lst_btn = (Button) findViewById(R.id.master_lst_btn);
        master_lst_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, master_frag)
                        .commit();
            }
        });


        Button insight_btn = (Button) findViewById(R.id.insight_btn);
        insight_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, insight_frag)
                        .commit();
            }
        });

    }




    // Fragment Listener classes. Must implement to run.
    @Override
    public void onDashboardInputSent(Uri uri) {
//        Log.d("Fragment Pressed:", "Dashboard");
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

}
