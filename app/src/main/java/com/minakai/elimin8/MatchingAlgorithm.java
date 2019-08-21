package com.minakai.elimin8;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// for guava implementation
//import java.util.Collection;
//import com.google.common.base.Predicates;
//import com.google.common.collect.Collections2;

public class MatchingAlgorithm {

    // array that stores user data
    List<List<String>> user_data;
    int flare_up_count = 0;

    // counter for the flare ups
    int flare_counter = 0;

    // master list of verified foods
    ArrayList<String> master_lst = new ArrayList<>();

    // list of unverified foods
    ArrayList<String> unverified_list = new ArrayList<>();

    // index for the column
    int flare_up_key = 0;
    int symptom_key = 0;
    int date_key = 0;
    int food_1_key = 0;
    int food_2_key = 0;
    int food_3_key = 0;

    public MatchingAlgorithm()
    {
    }

    // Called to produce matches
    public void findMatches()
    {
        //set the sample input
        user_data = setupSampleInput();

        iterateRows();
    }

    private List<List<String>> setupSampleInput()
    {
        List<List<String>> arr = new ArrayList<List<String>>();
        arr.add(new ArrayList<String>(Arrays.asList("Bumpy", "8/1/2019 2pm", "A", "B", "C")));
        arr.add(new ArrayList<String>(Arrays.asList("Bumpy", "8/5/2019 6pm", "A", "C", "D")));
        arr.add(new ArrayList<String>(Arrays.asList("Bumpy", "8/12/2019 4pm", "A", "C")));
        arr.add(new ArrayList<String>(Arrays.asList("Scaly", "8/14/2019 9am", "C", "B", "D")));
        arr.add(new ArrayList<String>(Arrays.asList("Scaly", "8/20/2019 12pm", "A", "B", "D")));
        arr.add(new ArrayList<String>(Arrays.asList("Scaly", "8/25/2019 3pm", "A", "B")));


        flare_up_count = arr.size();

        return arr;
    }

    private void iterateRows()
    {
        List<String> list = new ArrayList<String>();
        list.add("How are you");
        list.add("How you doing");
        list.add("Joe");
        list.add("Mike");



//        Collection<String> filtered = Collections2.filter(list,
//                Predicates.containsPattern("How"));
//        Log.d("CREATION", filtered.toString());


//        for (int count=0; count < flare_up_count; count++)
//        {
//            Log.d("CREATION", "Flare up #" + (count + 1) + " Entered by user.");
//
//            // current data we are using to search
//            List<String> current_data = user_data.get(count);
//
////            Log.d("CREATION", current_data.toString());
//
//            // past data that needs to be searched
//            String[][] search_data = self.test_data_df[(self.test_data_df[self.symptom_key] == current_series[self.symptom_key]) &
//                (self.test_data_df.index < count + 1)];
//        }


//        Log.d("CREATION", Arrays.toString(user_data[0]));
    }

    private void getCombinations()
    {

    }

    private void getMatchingCount()
    {

    }

    private void printPrediction()
    {

    }
}
