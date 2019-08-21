########################################################################################################################
###                                                Matching Algorithm
###
###    Matching Algorithm written in Python.
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
###    to run output read in output String through Java and print in Logcat.
###
###
########################################################################################################################
import pandas as pd
from itertools import combinations

class MatchingAlgorithm:
    def __init__(self):

        # df that stores user data
        self.user_data_df = pd.DataFrame()

        self.flare_up_count = 0
        # counter for the flare ups
        self.counter = 0

        # master list of verified foods
        self.master_lst = []

        # list of unverified foods
        self.unverified_lst = set()

        # keys for the df
        self.flare_up_key = 'Flare Up'
        self.symptom_key = 'Symptom'
        self.date_key = 'Date Recorded'
        self.food_1_key = 'Food #1'
        self.food_2_key = 'Food #2'
        self.food_3_key = 'Food #3'

        # output string
        self.output_str = ""

    # read in user data and set flare up count
    def set_up_user_input(self, java_array):
        user_data = []
        for nested_array in java_array:
            user_data.append(list(nested_array))

        self.user_data_df = pd.DataFrame(user_data, columns=[self.symptom_key, self.date_key, self.food_1_key, self.food_2_key, self.food_3_key])

        # self.output_str += self.user_data_df.to_string()

        # set flare up count
        self.flare_up_count = len(self.user_data_df.index)

    # core function called by main
    def run(self, java_array):
        self.output_str += 'Setting up user data...\n\n'
        self.set_up_user_input(java_array)

        self.iterate_rows()

        if self.output_str:
            return self.output_str
        else:
            return 'Output is Empty'

    # go through each row of data and find matches
    def iterate_rows(self):
        for count in range(self.flare_up_count):
            self.output_str += 'Flare Up #' + str(count + 1) + ' entered by user.\n'

            # current data we are using to search
            current_series = self.user_data_df.loc[count]

            # past data that needs to be searched
            search_df = self.user_data_df[(self.user_data_df[self.symptom_key] == current_series[self.symptom_key]) &
                                          (self.user_data_df.index < count)]

            if search_df.empty:
                self.output_str += 'Please enter more data for analysis.\n'
            else:
                # all the list of combinations
                comb_lst = self.get_combinations(current_series)

                # check combinations against past data and get the frequency count
                count_dict = self.get_matching_count(comb_lst, search_df)

                # print the prediction
                self.print_prediction(count_dict)

                # prompt for user verification

            self.output_str += '\n\n'

    def get_combinations(self, series):
        # get the list of the foods
        food_lst = [series['Food #' + str(count)] for count in range(1,4) if not pd.isna(series['Food #' + str(count)])]
        # get the list of the combinations
        comb_lst = [comb for comb_obj in [combinations(food_lst, l) for l in range(1, len(food_lst) + 1)] for comb in comb_obj]

        return comb_lst

    def get_matching_count(self, comb_lst, search_df):
        # combination count dictionary to be returned
        comb_count_dict = {comb: 0 for comb in comb_lst}

        # for all of the previous data with the same symptom
        for indx in search_df.index:
            # get all the combinations to check
            check_combs = self.get_combinations(search_df.loc[indx])

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
