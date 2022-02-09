import pandas as pd
import numpy as np
import random
from csv import writer
import csv
import math

def genPastDayInfectNum(totalVisited):
  #被调用, 以每一次每一栋楼每一天的total visited number进行对应计算
  infectedNum = totalVisited * (random.randint(0,2000)/10000)
  infectedNum = math.floor(infectedNum)
  return infectedNum

def genPast6DaysTotalVisitedNum():
  # 假设每一天每一栋楼的访问量为2000人次，波动+-20%
  totalVisitedNum = 2000 * (random.randint(8000,12000)/10000)
  # totalVisitedNum = math.floor(totalVisitedNum)
  infectedNum = genPastDayInfectNum(totalVisitedNum)
  percentage = (infectedNum/totalVisitedNum*100)
  return [math.floor(totalVisitedNum), math.floor(infectedNum), round(percentage,2)]

# def genOtherColumn():

def composeArr(buildingNameList):
  covidAllRow = []
  for i in range (len(buildingNameList)):
    for date in range (1,7): # 1,2,3,4,5,6
      covidSingleRow = []
      covidSingleRow.append(buildingNameList[i])
      covidSingleRow.append(date)
      num = genPast6DaysTotalVisitedNum()
      covidSingleRow.append(num[0])
      covidSingleRow.append(num[1])
      covidSingleRow.append(num[2])
      covidAllRow.append(covidSingleRow)
  return(covidAllRow)

def createCSV(buidingRow):
  header = ['BuildingName','Date','InfectedNum','TotalVistedNum','percentage']
  # open the file in the write mode
  with open('randomCovidData.csv', 'a+', encoding='UTF8', newline='') as f:
      # create the csv writer
      writer = csv.writer(f)
      # write a row to the csv file
      writer.writerow(header)
      for i in buidingRow:
        writer.writerow(i)

buildingNameList =  ["ARC","DEN","GRB","GWN","JHN","KNE","MGH","MNY","OUG","PAR","RAI","SAV","SMI","SUZ"]
# the array is a 3D array, [[[suz],[suz]],[[other],[other]],.....]
buidingRow = composeArr(buildingNameList)
createCSV(buidingRow)
# 生成了一个csv name: "randomCovidData.csv"