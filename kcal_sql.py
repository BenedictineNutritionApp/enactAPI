import pandas as pd
import numpy as np

# import re
import matplotlib.pyplot as plt
import seaborn as sns
import xgboost as xgb
# import eli5
# import shap
import datetime

from sklearn.model_selection import train_test_split
from xgboost import XGBRegressor
# from sklearn.metrics import mean_absolute_error
# from sklearn.model_selection import GridSearchCV
# from eli5.sklearn import PermutationImportance

import mysql.connector
from mysql.connector import Error

# Connect to SQL database
try:
    connection = mysql.connector.connect(host='127.0.0.1',
                                         database='health_app_test',
                                         user='root',
                                         password='root')
    
    orig_data = pd.read_sql('SELECT * FROM sensewhy_records', con=connection)

except mysql.connector.Error as e:
    print("Error reading data from MySQL table", e)
finally:
    if connection.is_connected():
        connection.close()
        print("MySQL connection is closed")

data = orig_data.copy()
data_copy = orig_data.copy()

# Remove irrelevant/empty cols
data = data.drop(['Project_Abbreviation', 'Participant_ID', 'Participant_Name', 'Interviewer_ID', 'Sequence_Number', 'Header_Notes', 'Trailer_Notes', 'QA_Status_Flag', 'Reviewer_ID', 'Date_of_Review', 'Gender', 'Date_of_Birth', 'Energy_Flag', 'Total_Fat_Flag', 'Total_Carbohydrate_Flag', 'Total_Protein_Flag', 'Alcohol_Flag', 'Caffeine_Flag', 'Calcium_Flag', 'Iron_Flag', 'Potassium_Flag', 'Sodium_Flag', 'Vitamin_C_Flag', 'Total_Folate_Flag', 'Total_Vitamin_A_Activity_Flag', 'Total_SFA_Flag', 'Total_Dietary_Fiber_Flag', 'Total_Sugars_Flag', 'Header_Data_Field_1_Descriptor', 'Header_Data_Field_1_Response', 'Header_Data_Field_2_Descriptor', 'Header_Data_Field_2_Response', 'Header_Data_Field_3_Descriptor', 'Header_Data_Field_3_Response', 'Header_Data_Field_4_Descriptor', 'Header_Data_Field_4_Response', 'Header_Data_Field_5_Descriptor', 'Header_Data_Field_5_Response', 'Trailer_Data_Field_1_Descriptor', 'Trailer_Data_Field_1_Response', 'Trailer_Data_Field_2_Descriptor', 'Trailer_Data_Field_2_Response', 'Trailer_Data_Field_3_Descriptor', 'Trailer_Data_Field_3_Response'], axis=1)

cols = data.columns.values.tolist()

# Use heatmap to produce a correlations list to detect relevant relationships

###
### PREDICT kcal
###

X = data.copy()
y = X['Energy_kcal']
X = X.drop(['Energy_kcal', 'Date_of_Intake'], axis=1)

# # Parameter tuning
# param_grid = {'n_estimators': [5, 10, 15, 20, 50, 100],
#               'learning_rate': [0.01, 0.05, 0.1, 0.2, 0.3],
#               'n_jobs': [1, 2, 3, 4]}
# 
# # Random split
# X_train, X_valid, y_train, y_valid = train_test_split(X, y, train_size=0.6, test_size=0.4, random_state=0)
# X_train, X_valid = X_train.align(X_valid, join='left', axis=1)
# 
# # Model
# model_base = XGBRegressor()
# model_base.fit(X_train, y_train, verbose=False)
# model_base_preds = model_base.predict(X_valid)

# From permutation importance and heatmap
X_tune = X.drop(['Intake_Amount', 'Intake_Reliability', 'Total_Protein_g', 'Alcohol_g', 'Caffeine_mg', 'Calcium_mg', 'Iron_mg', 'Potassium_mg', 'Sodium_mg', 'Total_Folate_mcg', 'Total_Vitamin_A_Activity_Retinol_Activity_Equivalents_mcg', 'Total_Dietary_Fiber_g'], axis=1)
# cols = X_tune.columns.values.tolist()
# 
# X_train_fixed, X_valid_fixed, y_train_fixed, y_valid_fixed = train_test_split(X_tune, y, train_size=0.6, test_size=0.4, shuffle=False)
# X_train_fixed, X_valid_fixed = X_train_fixed.align(X_valid_fixed, join='left', axis=1)
# 
# model_base_tune = XGBRegressor()
# model_base_tune.fit(X_train_tune, y_train, verbose=False)
# model_base_tune_preds = model_base_tune.predict(X_valid_tune)
# # No difference in mae before and after tuning

# Fixed split
# train_pct_index = int(0.6 * len(X_tune))

# Starts to predict 7 days in advance when at least 5 days have been recorded
if len(X_tune) > 11:
    # Always predict 7 days in advance
    X_train_fixed, X_valid_fixed = X[:len(X_tune)-7], X[len(X_tune)-7:]
    y_train_fixed, y_valid_fixed = y[:len(X_tune)-7], y[len(X_tune)-7:]
    
    model_base_fixed = XGBRegressor()
    model_base_fixed.fit(X_train_fixed, y_train_fixed, verbose=False)
    model_base_fixed_preds = model_base_fixed.predict(X_valid_fixed)
    # mae smaller
    
    ###
    ### PLOT
    ###
    
    kcal_data = np.concatenate((y_train_fixed.values, model_base_fixed_preds), axis=0)
    recommended_kcal = np.array([2000 for i in range(len(orig_data['Date_of_Intake']))])
    
    # Need to adapt to sql
    plt.plot(orig_data['Date_of_Intake'], orig_data['Energy_kcal'], label='actual')
    plt.scatter(orig_data['Date_of_Intake'][len(X_tune)-7:], model_base_fixed_preds, c='orange', label='predicted')
    plt.plot(orig_data['Date_of_Intake'], recommended_kcal, 'r--', label='recommended')
    plt.xlabel('Date')
    plt.ylabel('Energy (kcal)')
    plt.legend(loc='upper right')
    plt.show()
     
    # Trendline/trajectory of kcal
    data_copy['date_ordinal'] = pd.to_datetime(data_copy['Date_of_Intake']).apply(lambda date: date.toordinal())
    
    print(data_copy['date_ordinal'])
    ax = sns.regplot(data=data_copy, x='date_ordinal', y='Energy_kcal')
    date_convert = []
    for ordinal in data_copy['date_ordinal']:
        date = datetime.date.fromordinal(ordinal)
        date_convert.append(date)
    ax.set_xticklabels(date_convert)
    ax.set_xlabel('date')
    ax.axhline(2000, linestyle='--', color='r')
    plt.show()