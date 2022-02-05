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

package pathfinder.textInterface;

import graph.Graph;
import graph.Graph.Edge;
import pathfinder.CampusMap;
import pathfinder.datastructures.Path;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Pathfinder represents a complete application capable of responding to user prompts to provide
 * a variety of information about campus buildings and paths between them.
 */
public class Pathfinder {

    // This class does not represent an ADT.

    /**
     * The main entry point for this application. Initializes and launches the application.
     *
     * @param args The command-line arguments provided to the system.
     */
    public static void main(String[] args) {
        CampusMap map = new CampusMap("campus_buildings.tsv", "campus_paths.tsv");
        TextInterfaceView view = new TextInterfaceView();
        TextInterfaceController controller = new TextInterfaceController(map, view);
        //
        view.setInputHandler(controller);
        controller.launchApplication();
    }

    /**
     * Finds a shortest path between two points in a graph.
     * @param <E>  Type of points in graph
     * @param graph a Graph being searched
     * @param start Starting point
     * @param dest Destination point
     * @spec.requires graph != null, start != null, dest != null
     * @throws IllegalArgumentException if <var>start</var> or <var>dest</var> is not in graph
     * @return A path showing the shortest path between <var>start</var> and <var>dest</var>;
     * if no path found, return <var>null</var>
     */
    public static <E> Path<E> findPath(Graph<Double, E> graph, E start, E dest) {
        if(!graph.containNode(start) || !graph.containNode(dest)) {
            throw new IllegalArgumentException();
        }
        PriorityQueue<Path<E>> active = new PriorityQueue<>(new Comparator<Path<E>>() {
            @Override
            public int compare(Path<E> path1, Path<E> path2) {
                return Double.compare(path1.getCost(), path2.getCost());
            }
        });
        // Each element is a path from start to a given node.
        // A path's priority in the queue is the total cost of that path.
        // Nodes for which no path is known yet are not in the queue.
        Set<E> finished = new HashSet<>();
        // Initially we only know of the path from start to itself, which has
        // a cost of zero because it contains no edges.
        active.add(new Path<E>(start)); // Add a path from start to itself to active
        while(!active.isEmpty()) {
            // minPath is the lowest-cost path in active and,
            // if minDest isn't already 'finished,' is the
            // minimum-cost path to the node minDest
            Path<E> minPath = active.remove();
            E minDest = minPath.getEnd();

            if(minDest.equals(dest)) {
                return minPath;
            }

            if(finished.contains(minDest)) {
                continue;
            }

            Set<Edge<Double, E>> edges = graph.getEdges(minDest); // For all children of minDest
            for(Edge<Double, E> edge : edges) {
                // If we don't know the minimum-cost path from start to child,
                // examine the path we've just found
                E child = edge.getDestination();
                Double length = edge.getLabel();
                if(!finished.contains(child)) {
                    Path<E> newPath = minPath.extend(child, length);
                    active.add(newPath);
                }
            }

            finished.add(minDest);
        }
        // If the loop terminates, then no path exists from start to dest.
        // The implementation should indicate this to the client.
        return null;
    }
}
