init python in var:

    import json

    toFullName_str = u'{"":"","ACC": "John M. Wallace Hall", "AER": "Aerospace & Engineering Research Building", "ALB": "Allen Library", "AND": "Anderson Hall", "ARC": "Architecture Hall", "ART": "Art Building", "ATG": "Atmos Sci/Geophysics", "BAG": "Bagley Hall", "BGH": "Botany Greenhouse", "BIOE": "W.H. Foege Bioengineering Building", "BLD": "Bloedel Hall", "BMM": "Burke Memorial Museum", "BNS": "Benson Hall", "BRY": "Bryant Building", "CDH": "Condon Hall", "CHB": "Chemistry Building", "CHCL": "Center on Human Development and Disability", "CHL": "Chemistry Library Building", "CLK": "Clark Hall", "CMA": "Ceramic and Metal Arts Building", "CMU": "Communications Building", "CNH": "Canoe House", "COH": "Children\'s Hospital", "CSE": "Paul G. Allen Center for Computer Science & Engineering", "CSH": "Conibear Shellhouse", "DEN": "Denny Hall", "DEM": "Dempsey Hall", "DRC": "Douglas Research Conservatory", "DSC": "3941 University Way N.E.", "ECC": "Ethnic Cultural Center", "EDP": "Edmundson Pavilion", "EEB": "Electrical Engineering Building", "EGA": "Engineering Annex", "EGL": "Eagleson Hall", "ELB": "Engineering Library", "EXED": "Bank of America Executive Education Center and Foster Library", "FIS": "Fisheries Center", "FLK": "Fluke Hall", "FRH": "Friday Harbor Laboratories", "FSH": "Fisheries Building", "FTR": "Fisheries Teaching Research Building", "GA1": "Guthrie Annex 1", "GA2": "Guthrie Annex 2", "GA3": "Guthrie Annex 3", "GDR": "Golf Driving Range", "GHH": "Group Health Hospital", "GLD": "Gould Hall", "GNOM": "W.H. Foege Genome Sciences Building", "GRB": "Gerberding Hall", "GTH": "Guthrie Hall", "GUG": "Guggenheim Hall", "GWN": "Gowen Hall", "HAG": "Henry Art Gallery", "HCK": "Hitchcock Hall", "HGT": "Haggett Hall", "HHL": "Harris Hydraulics Lab", "HLL": "Hall Health Center", "HMC": "Harborview Medical Center", "HND": "Henderson Hall", "HPT": "Hughes Penthouse Theater", "HRC": "Fred Hutchinson Cancer Research Center", "HS4": "Health Sciences Annex 4", "HSA": "Health Sciences Building wings A and B and  C", "HSB": "Health Sciences Building wings AA and BB", "HSD": "Health Sciences Building wings D and F and G and H", "HSE": "Health Sciences Building wing E", "HSI": "Health Sciences Building wing I", "HSJ": "Health Sciences Building wing J", "HSK": "Health Sciences Building wing K", "HSRR": "Health Sciences Building wing RR", "HST": "Health Sciences Building wing T", "HUB": "Student Union Building", "HUT": "Hutchinson Hall", "ICH": "Cunningham Hall", "ICT": "Samuel E. Kelly Ethnic Cultural Center", "IMA": "Intramural Activities Building", "JHA": "Johnson Annex", "JHN": "Johnson Hall", "KIN": "Kincaid Hall", "KNE": "Kane Hall", "LAW": "William H. Gates Hall", "LEW": "Lewis Hall", "LOW": "Loew Hall", "MAR": "Marine Studies Building", "MEB": "Mechanical Engineering Building", "MGH": "Mary Gates Hall", "MKZ": "Mackenzie Hall", "MLR": "Miller Hall", "MNY": "Meany Hall", "MOR": "More Hall", "MSB": "Marine Sciences Building", "MUE": "Mueller Hall", "MUS": "Music Building", "NOC": "Not On Campus", "NRB": "Nuclear Reactor Building", "OBS": "Observatory", "OCE": "Oceanography Building", "OCN": "Ocean Sciences Building", "OTB": "Oceanography Teaching Building", "OUG": "Odegaard Undergrad Library", "PAA": "Physics Astronomy Auditorium(13-K)", "PAB": "Physics Astronomy Bar", "PAT": "Physics Astronomy Tower(12-J)", "PAR": "Parrington Hall", "PCAR": "Paccar Hall", "PDL": "Padelford Hall", "PHT": "Playhouse Theater", "PLT": "Plant Laboratory", "PSC": "Pacific Science Center", "RAI": "Raitt Hall", "ROB": "Roberts Hall", "RTB": "Benjamin Hall Interdiciplinary Research and Technology Building", "RVC": "Roosevelt Commons", "SAV": "Savery Hall", "SGS": "Community Design Building-3947 Univ Way", "SIG": "Sieg Hall", "SMI": "Smith Hall", "SMZ": "Schmitz Hall", "SOCC": "South Campus Center", "SUZ": "Suzzallo Library", "SWS": "Social Work/Speech and Hearing Sciences Building", "TER": "Terry Hall (10-F)", "TGB": "Graves Building", "THO": "Thomson Hall", "UMSP": "University Medical Center and Surgery and Treatment Pavilion", "WBA": "Will be assigned", "WCL": "Wilson Ceramic Laboratory", "WFS": "Winkenwerder Forest Science Lab"}'

    supported_buildigns = ['ARC', 'DEN', 'GRB', 'GWN', 'JHN', 'KNE', 'MGH', 'MNY', 'ODE', 'OUG', 'PAR', 'RAI', 'SAV', 'SMI', 'SUZ']

    focused_buildings = ['PAR', 'MGH', 'KNE', 'SUZ']

    def get_key(val, d):
        for key, value in d.items():
            if val == value:
                return key

    toFullName = json.loads(toFullName_str)

    # with open(curr_dirr+'/game/building_names.json',"r") as f:


    # with open(curr_dirr+"/game/data.json","r") as f:


    def new_building(new_building):
        if new_building in supported_buildigns:
            if focused_buildings[len(focused_buildings)-1] == '':
                focused_buildings.remove("")
            focused_buildings.append(new_building)
        elif get_key(new_building, toFullName) in supported_buildigns:
            if focused_buildings[len(focused_buildings)-1] == '':
                focused_buildings.remove("")
            focused_buildings.append(get_key(new_building, toFullName))
        if len(focused_buildings) % 2 == 1:
            focused_buildings.append('')
        #or new_building in toFullName or new_building in toFullName.values():


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