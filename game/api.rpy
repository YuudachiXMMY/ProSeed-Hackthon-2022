init python in var:

    import json

    focused_buildings = ['ARC', 'DEN', 'MNY', 'SUZ']

    with open('F:\Files\University of Washington\events\ProSeed-Hackthon-2022\game/building_names.json') as f:

        toFullName = json.load(f)

    with open('F:\Files\University of Washington\events\ProSeed-Hackthon-2022\game/data.json') as f:
        data = json.load(f)

    def new_building(new_building):
        if new_building in toFullName or new_building in toFullName.values():
            focused_buildings.append(new_building)

# init python:
    # import pandas as pd
    # import numpy as np
    # from csv import writer

    # def parseCalendar():
    #     dfCalendar = pd.DataFrame(pd.read_csv("CalendarTemp.csv"))
    #     return dfCalendar

    # def searchByStartDate(df, startDate):
    #     by11 = df['StartDate'] == startDate
    #     ws = df.loc[by11, ['StartTime', 'EventName', 'BuildingName']]
    #     dataSortByStartTime = ws.sort_values(by=["StartTime"], ascending=True)
    #     final = {}
    #     for index, row in dataSortByStartTime.iterrows():
    #         starttime = row[0]
    #         eventtime = row[1]
    #         buildingtime = row[2]
    #         if starttime in final.keys():
    #             final[starttime].append([eventtime, buildingtime])
    #         else:
    #             final[starttime] = [[eventtime, buildingtime]]
    #     return final

    # def searchByDate(df):
    #     uw = df.groupby('StartDate')
    #     event_date = {}
    #     for name,group in uw:
    #         print_final = searchByStartDate(df, name)
    #         if name in event_date:
    #             event_date[name].append([print_final])
    #         else:
    #             event_date[name] = print_final
    #     return event_date

    # def parseInput(eventlist):
    #     with open('CalendarTemp.csv','a+', newline='') as f:
    #         csv_writer = writer(f)
    #         csv_writer.writerow(eventlist)


    # # df = parseCalendar()
    # # res = searchByDate(df)
    # # for i in res.keys():
    # # print(res[i])
    # # print()
    # # parseInput(['Math 127','2/21/2022','7:10 PM','2/21/2022','10:00 PM',False,'Study Math','Kane Hall'])