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
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import pathfinder.parser.CampusBuilding;
import pathfinder.parser.CampusPath;
import pathfinder.parser.CampusPathsParser;
import pathfinder.datastructures.Dijkstra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CampusMap implements ModelAPI {

    public List<CampusBuilding> buildings = CampusPathsParser.parseCampusBuildings("campus_buildings.csv");
    public List<CampusPath> paths = CampusPathsParser.parseCampusPaths("campus_paths.csv");

    // Build Campus map Graph
    public Graph<Point, Double> createMap(){
        Graph<Point, Double> cMap = new Graph<>();
        for (CampusPath path : paths) {
            Point newPoint1 = new Point(path.getX1(), path.getY1());
            cMap.insertNode(new Graph.node<>(newPoint1));
            Point newPoint2 = new Point(path.getX2(), path.getY2());
            cMap.insertNode(new Graph.node<>(newPoint2));
        }

        for (CampusPath connection : paths) {
            cMap.insertEdge(new Graph.node<>(new Point(connection.getX1(), connection.getY1())),
                    new Graph.node<>(new Point(connection.getX2(), connection.getY2())), connection.getDistance());
        }

        return cMap;
    }

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
    public Path<Graph.node<Point>> findShortestPath(String startShortName, String endShortName) {
        Graph<Point, Double> cMap = createMap();
        // finds buildings we will be pathing to
        Point start = null;
        Point end = null;
        System.out.println("burh 1");
        if (!shortNameExists(startShortName) | !shortNameExists(endShortName)) {
            System.out.println("burh 2");
            throw new IllegalArgumentException();
        }
        for (CampusBuilding building : buildings){
            if (building.getShortName().equals(startShortName)){
                start = new Point(building.getX(), building.getY());;
            }
            if (building.getShortName().equals(endShortName)){
                end = new Point(building.getX(), building.getY());
            }
        }

        Dijkstra<Point> dij = new Dijkstra<>();
        return dij.Dij(cMap, start, end);
    }

}
