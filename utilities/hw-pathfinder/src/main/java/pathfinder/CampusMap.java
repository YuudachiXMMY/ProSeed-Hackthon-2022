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

package pathfinder;

import graph.Graph;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;
import pathfinder.parser.CampusBuilding;
import pathfinder.parser.CampusPath;
import pathfinder.parser.CampusPathsParser;
import pathfinder.textInterface.Pathfinder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CampusMap represents a model to which shows a real map for University of Washington
 * and is able to find shortest paths between buildings.
 */
public class CampusMap implements ModelAPI {

    // Representation invariant:
    //      graph != null, nameReference != null, pointReference != null
    //      nameReference and pointReference contains no null keys/values
    //      nameReference and pointReference contain the same keys

    // Abstraction function: AF(this) = A CampusMap m such that
    //                                  this.graph contains paths of m
    //                                  this.nameReference contains building names of m
    //                                  this.pointReference contains the coordinates of buildings of m

    public static final boolean DEBUG = false;

    /** The graph representing points and edges with length. */
    private Graph<Double, Point> graph;
    /** maps uses short name as key and long name as values. */
    private Map<String, String> nameReference;
    /** maps uses short name as key and points as values. */
    private Map<String, Point> pointReference;

    /**
     * Construct a CampusMap from dataset of buildings and paths file to creat a graph representing
     * the paths with distance between campus buildings.
     * @param buildingsFile a Filename representing a dataset of campus buildings
     * @param pathFile a Filename representing a dataset of campus paths
     * @spec.requires Files are valid
     *      the file for buildings is in the folloing form:
     *          shortName       longName        x       y
     *      the file for paths is in the folloing form:
     *          x1      y1      x2      y2      distance
     * @throws IllegalArgumentException if <var>buildingsFile</var> or <var>pathsFilename</var> are null
     *
     */
    public CampusMap(String buildingsFile, String pathFile) {
        if(buildingsFile == null || pathFile == null) {
            throw new IllegalArgumentException();
        }
        graph = new Graph<>();
        nameReference = new HashMap<>();
        pointReference = new HashMap<>();

        List<CampusBuilding> buildings = CampusPathsParser.parseCampusBuildings(buildingsFile);
        List<CampusPath> paths = CampusPathsParser.parseCampusPaths(pathFile);
        for(CampusBuilding building : buildings) {
            graph.addNode(new Point(building.getX(), building.getY()));
            nameReference.put(building.getShortName(), building.getLongName());
            pointReference.put(building.getShortName(), new Point(building.getX(),building.getY()));
        }
        for(CampusPath path: paths) {
            Point p1 = new Point(path.getX1(), path.getY1());
            Point p2 = new Point(path.getX2(), path.getY2());
            if(!graph.containNode(p1)) {
                graph.addNode(p1);
            }
            if(!graph.containNode(p2)) {
                graph.addNode(p2);
            }
            graph.addEdge(p1, p2, path.getDistance());
        }
        checkRep();
    }

    @Override
    public boolean shortNameExists(String shortName) {
        checkRep();
        return nameReference.containsKey(shortName);
    }

    @Override
    public String longNameForShort(String shortName) {
        checkRep();
        if(!nameReference.containsKey(shortName)) {
            throw new IllegalArgumentException();
        }
        return nameReference.get(shortName);
    }

    @Override
    public Map<String, String> buildingNames() {
        checkRep();
        return new HashMap<>(nameReference);
    }

    @Override
    public Path<Point> findShortestPath(String startShortName, String endShortName) {
        checkRep();
        if(startShortName == null || endShortName == null) {
            throw new IllegalArgumentException();
        }
        if(!pointReference.containsKey(startShortName)) {
            throw new IllegalArgumentException();
        }
        if(!pointReference.containsKey(endShortName)) {
            throw new IllegalArgumentException();
        }
        Point start = pointReference.get(startShortName);
        Point dest = pointReference.get(endShortName);
        checkRep();
        return Pathfinder.findPath(graph, start, dest);
    }

    /**
     * Throws exceptions if RI is violated.
     */
    private void checkRep() {
        assert graph != null;
        assert nameReference != null;
        assert nameReference.keySet().equals(pointReference.keySet());
        assert pointReference != null;
        if(DEBUG) {
            assert pointReference.keySet().equals(nameReference.keySet());
            for(String name : nameReference.keySet()) {
                assert name != null;
                assert nameReference.get(name) != null;
            }
            for(String name : pointReference.keySet()) {
                assert name != null;
                assert pointReference.get(name) != null;
            }
        }
    }
}
