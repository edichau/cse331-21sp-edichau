/*
 * Copyright (C) 2021 Kevin Zatloukal.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2021 for purposes of
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
import graph.Graph;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;

import static spark.Spark.get;

public class SparkServer {

    public static void main(String[] args) {
        CORSFilter corsFilter = new CORSFilter();
        corsFilter.apply();
        // The above two lines help set up some settings that allow the
        // React application to make requests to the Spark server, even though it
        // comes from a different server.
        // You should leave these two lines at the very beginning of main().

        CampusMap campusMap = new CampusMap();
        Gson gson = new Gson();

        get("/campusBuilding", (request, response) -> {
            return gson.toJson(campusMap.buildingNames());
        });

        get("/findPath", (request, response) -> {
            response.header("Content-Type", "application/json");
            Path<Graph.node<Point>> path = campusMap.findShortestPath(request.queryParams("startBuilding"), request.queryParams("endBuilding"));
            return gson.toJson(path);
        });
    }

}
