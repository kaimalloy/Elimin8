########################################################################################################################
###                                      Python Backend for Elimin8
###
###    Python Backend that generates the Matching Algorithm.
###
###    Created :   2019-08-20
###    Modified:   2019-08-20
###
###
###    Changes:
###       - Input is made to be from JAVA file
###
###
###    Created by: Kai Malloy
###
###
########################################################################################################################
import pandas as pd
from itertools import combinations

class PyBackend:
    def __init__(self):
        # keys for the df
        self.flare_up_key = 'Flare Up'
        self.symptom_key = 'Symptom'
        self.date_key = 'Date Recorded'
        self.food_key = 'Foods'

        # df that stores user data
        self.user_data_df = pd.DataFrame(columns=[self.symptom_key, self.date_key, self.food_key])

        # series that stores the new flare up input
        self.flare_up_series = pd.Series()

        # master list of verified foods
        self.master_lst = []

        # list of unverified foods
        self.unverified_lst = set()

        # output string
        self.output_str = ""

    def get_prediction(self, symptom, date, foods):
        self.output_str += 'New Flare Up Entered.\n'

        # set the new flare up
        self.flare_up_series = pd.Series([symptom, date, list(foods)], index=[self.symptom_key, self.date_key, self.food_key])

        if self.user_data_df.empty:
            self.output_str += 'Please enter more data for analysis.\n'
        else:
            # all the list of combinations
            comb_lst = self.get_combinations(self.flare_up_series[self.food_key])

            # check combinations against past data and get the frequency count
            count_dict = self.get_matching_count(comb_lst)

            # print the prediction
            self.print_prediction(count_dict)

        # add current flare up to user data df
        self.user_data_df = self.user_data_df.append(self.flare_up_series, ignore_index=True)

        # prompt for user verification

        self.output_str += 'New User Data DF: \n' + self.user_data_df.to_string() + '\n\n'

        return self.output_str

    def get_combinations(self, food_lst):
        # get the list of the combinations
        comb_lst = [comb for comb_obj in [combinations(food_lst, l) for l in range(1, len(food_lst) + 1)] for comb in comb_obj]

        return comb_lst

    def get_matching_count(self, comb_lst):
        # combination count dictionary to be returned
        comb_count_dict = {comb: 0 for comb in comb_lst}

        # for all of the previous data with the same symptom
        for indx in self.user_data_df.index:
            # get all the combinations to check
            check_combs = self.get_combinations(self.user_data_df.iloc[indx][self.food_key])

            # get the frequency of the combinations of the current data
            for comb in comb_lst:
                if comb in check_combs:
                    comb_count_dict[comb] += 1

        return comb_count_dict

    def print_prediction(self, comb_dict):
        # sort by dictionary value
        sorted_comb_lst = sorted(comb_dict.items(), key=lambda x: x[1], reverse=True)

        # Max cap, difference cap, and master list check added
        prediction_lst = []
        print_string = ''

        prediction_made = False
        prediction_count = 1
        prev_food = ()
        for tup in sorted_comb_lst:
            if not prediction_made:
                # max cap
                if prediction_count >= 5:
                    prediction_made = True
                # frequency == 0
                elif tup[1] == 0:
                    prediction_made = True
                # difference cap
                elif prev_food and (prev_food[1] - tup[1] >= 3):
                    prediction_made = True
                # master list check
                elif list(tup[0]) in self.master_lst:
                    prediction_lst.append(tup)
                    prediction_made = True
                else:
                    prediction_lst.append(tup)

                # used by max cap
                prediction_count += 1

            print_string += str(tup[1]) + ' - > ' + str(list(tup[0])) + '\n'

        self.output_str += 'Prediction:\n'
        for prediction in prediction_lst:
            self.output_str += str(prediction[1]) + ' - > ' + str(list(prediction[0])) + '\n'

        self.output_str += 'All Combinations:\n'
        self.output_str += print_string + '\n'


    def clear_output_str(self):
        self.output_str = ""
