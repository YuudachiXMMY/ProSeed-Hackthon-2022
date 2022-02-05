/*
 * Copyright (C) 2021 Hal Perkins.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Winter Quarter 2021 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package campuspaths;

import campuspaths.utils.CORSFilter;
import com.google.gson.Gson;
import pathfinder.CampusMap;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.util.Map;
import java.util.TreeMap;

public class SparkServer {

    public static void main(String[] args) {
        CORSFilter corsFilter = new CORSFilter();
        corsFilter.apply();
        // The above two lines help set up some settings that allow the
        // React application to make requests to the Spark server, even though it
        // comes from a different server.
        // You should leave these two lines at the very beginning of main().

        // Construct the CampusMap
        CampusMap map = new CampusMap("campus_buildings.tsv", "campus_paths.tsv");
        Gson gson = new Gson();
        Spark.get("/buildings", new Route(){
            @Override
            public Object handle(Request request, Response response) throws Exception {
                Map<String, String> buildings = new TreeMap<>(map.buildingNames());
                return gson.toJson(buildings);
            }
        });
        Spark.get("/findPath", new Route(){
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String start = request.queryParams("start");
                String end = request.queryParams("end");
                if(start == null || end == null) {
                    Spark.halt(404);
                }
                Path<Point> path = map.findShortestPath(start, end);
                return gson.toJson(path);
            }
        });
    }
}
