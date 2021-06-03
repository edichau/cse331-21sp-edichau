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

package pathfinder;

import graph.Graph;
import marvel.MarvelParser;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import pathfinder.parser.CampusBuilding;
import pathfinder.parser.CampusPath;
import pathfinder.parser.CampusPathsParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CampusMap implements ModelAPI {

    public List<CampusBuilding> buildings = CampusPathsParser.parseCampusBuildings("campus_buildings.csv");
    public List<CampusPath> paths = CampusPathsParser.parseCampusPaths("campus_paths.csv");

    /**
     * @param shortName The short name of a building to query.
     * @return {@literal true} iff the short name provided exists in this campus map.
     */
    @Override
    public boolean shortNameExists(String shortName) {
        for (CampusBuilding building : buildings){
            if(building.getShortName().equals(shortName))
                return true;
        }
        return false;
    }

    /**
     * @param shortName The short name of a building to look up.
     * @return The long name of the building corresponding to the provided short name.
     * @throws IllegalArgumentException if the short name provided does not exist.
     */
    @Override
    public String longNameForShort(String shortName) {
        for (CampusBuilding building : buildings){
            if(building.getShortName().equals(shortName))
                return building.getLongName();
        }
        throw new IllegalArgumentException("short name provided does not exist");
    }

    /**
     * @return A mapping from all the buildings' short names to their long names in this campus map.
     */
    @Override
    public Map<String, String> buildingNames() {
       Map<String, String> ret = new HashMap<>();
        for (CampusBuilding building : buildings){
            ret.put(building.getShortName(), building.getLongName());
        }
       return ret;
    }

    /**
     * Finds the shortest path, by distance, between the two provided buildings.
     *
     * @param startShortName The short name of the building at the beginning of this path.
     * @param endShortName   The short name of the building at the end of this path.
     * @return A path between {@code startBuilding} and {@code endBuilding}, or {@literal null}
     * if none exists.
     * @throws IllegalArgumentException if {@code startBuilding} or {@code endBuilding} are
     *                                  {@literal null}, or not valid short names of buildings in
     *                                  this campus map.
     */
    @Override
    public Path<Point> findShortestPath(String startShortName, String endShortName) {
        // Build Campus map Graph

        Graph<CampusPath, Double> cMap = new Graph<>();
        for (CampusPath path : paths) {
            cMap.insertNode(new Graph.node<>(path));
            for (CampusPath connect : paths) {
                if (path.getX1() == connect.getX1() && path.getX2() == connect.getX2() && path.getY1() == connect.getY1() && path.getY2() == connect.getY2()) {
                    cMap.insertEdge(new Graph.node<>(path), new Graph.node<>(connect), connect.getDistance());
                }
            }
        }

        // finds buildings we will be pathing to
        CampusBuilding start = null;
        CampusBuilding end = null;
        for (CampusBuilding building : buildings){
            if (building.getShortName().equals(startShortName)){
                start = building;
            }
            if (building.getShortName().equals(endShortName)){
                end = building;
            }
        }

        Double startX = start.getX();
        Double startY = start.getY();
        Double endX = end.getX();
        Double endY = end.getY();


    }

}
