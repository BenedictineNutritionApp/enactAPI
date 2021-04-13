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
from sklearn.metrics import mean_absolute_error
from sklearn.model_selection import GridSearchCV
# from eli5.sklearn import PermutationImportance

import mysql.connector
from mysql.connector import Error

# Connect to SQL database
try:
    connection = mysql.connector.connect(host='127.0.0.1',
                                         database='health_app_test',
                                         user='root',
                                         password='root')
    
    orig_data = pd.read_sql('SELECT * FROM weight_food', con=connection)

except mysql.connector.Error as e:
    print("Error reading data from MySQL table", e)
finally:
    if connection.is_connected():
        connection.close()
        print("MySQL connection is closed")

data = orig_data.copy()

# Remove rows that only have weight and NaN for the other columns
data.drop(data.loc[0:150].index, inplace=True)
data = data.reset_index(drop=True)

# Only have rows with non-NaN weights
data = data[data['weight'].notna()]
data = data.reset_index(drop=True)

weight_loss = []
i = 1
while i < data.shape[0]:
    weight_loss.append(data.iloc[i-1][1]-data.iloc[i][1])
    i += 1

data.drop([0], inplace=True)
data = data.reset_index(drop=True)
data['weight_loss'] = weight_loss

data = data[data['calories'].notna()]
data = data.reset_index(drop=True)

# print(data.head)
# Use heatmap to find correlations list
def dataCopy_heatmap_corr():
    dataCopy = data.copy()
#     dataCopy = dataCopy.drop(columns=['participantNumber', 'participantId', 'dateEaten', 'dateAdded', 'foodDocId', 'foodName', 'foodType', 'servingName'])

    plt.figure(figsize=(16,10))
    corr = dataCopy.corr()
    dataCopy_heatmap = sns.heatmap(corr, annot = True, vmin=-1, vmax=1, center= 0)
    plt.show()
    
    c1 = corr.abs().unstack()
    c1.sort_values(ascending=False)
    
    list = corr[corr < 1].unstack().transpose()\
    .sort_values(ascending=False)\
    .drop_duplicates()

    print(list[list > 0.6])
    
# dataCopy_heatmap_corr()
# Sugar, carb, step are the only positively correlated features to weight
# But nothing is highly correlated to weight

### 
### PREDICT WEIGHT
###

X = data.copy()
y = X['weight']
# print(X.columns)
X = X.drop(['date', 'weight', 'calories', 'fat', 'protein', 'saturated_fat', 'fiber', 'cholesterol', 'sodium', 'weight_loss'], axis=1)
# print(X.columns)

# Parameter tuning
param_grid = {'n_estimators': [5, 10, 15, 20, 50, 100],
              'learning_rate': [0.01, 0.05, 0.1, 0.2, 0.3],
              'n_jobs': [1, 2, 3, 4]}

# Random split
X_train, X_valid, y_train, y_valid = train_test_split(X, y, train_size=0.8, test_size=0.2, random_state=0)
X_train, X_valid = X_train.align(X_valid, join='left', axis=1)

# Model
model_base = XGBRegressor()
model_base.fit(X_train, y_train, verbose=False)
model_base_preds = model_base.predict(X_valid)
# mae_base = mean_absolute_error(y_valid, model_base_preds)
# print("Mean Absolute Error:", mae_base)

# # Tuning
# grid_model_base = GridSearchCV(model_base, param_grid, scoring='r2', cv=5)
# grid_model_base.fit(X_train, y_train)
# print(grid_model_base.best_params_)
# 
# # After parameter tuning
# model_tune = XGBRegressor(learning_rate=0.3, n_estimators=100, n_jobs=1)
# model_tune.fit(X_train, y_train, verbose=False)
# model_tune_preds = model_tune.predict(X_valid)
# mae_tune = mean_absolute_error(y_valid, model_tune_preds)
# print("Mean Absolute Error (protein_tune):", mae_tune)

# No difference in mae before and after tuning

# Fixed split
# train_pct_index = int(0.8 * len(X))
X_train_fixed, X_valid_fixed = X[:len(X)-7], X[len(X)-7:]
y_train_fixed, y_valid_fixed = y[:len(X)-7], y[len(X)-7:]

model_base_fixed = XGBRegressor()
model_base_fixed.fit(X_train_fixed, y_train_fixed, verbose=False)
model_base_fixed_preds = model_base_fixed.predict(X_valid_fixed)
# mae_base_fixed = mean_absolute_error(y_valid_fixed, model_base_fixed_preds)
# print("Mean Absolute Error:", mae_base_fixed)

###
### PLOT
###

weight_data = np.concatenate((y_train.values, model_base_preds), axis=0)
# recommended_kcal = np.array([2000 for i in range(len(orig_data['date']))])

# Need to adapt to sql
plt.plot(data['date'], data['weight'], label='actual')
plt.scatter(data['date'][len(X)-7:], model_base_fixed_preds, c='orange', label='predicted')
# plt.plot(orig_data['date'], recommended_kcal, 'r--', label='recommended')
plt.xlabel('Date')
plt.ylabel('Weight')
plt.legend(loc='upper right')
plt.show()

# # Trendline/trajectory of kcal
# data_copy['date_ordinal'] = pd.to_datetime(data_copy['Date_of_Intake']).apply(lambda date: date.toordinal())
# ax = sns.regplot(data=data_copy, x='date_ordinal', y='Energy_kcal')
# date_convert = []
# for ordinal in data_copy['date_ordinal']:
#     date = datetime.date.fromordinal(ordinal)
#     date_convert.append(date)
# ax.set_xticklabels(date_convert)
# ax.set_xlabel('date')
# ax.axhline(2000, linestyle='--', color='r')
# plt.show()