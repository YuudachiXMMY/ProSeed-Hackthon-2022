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

package pathfinder.scriptTestRunner;

import graph.Graph;
import graph.Graph.Edge;
import marvel.MarvelPaths;
import pathfinder.datastructures.Path;
import pathfinder.textInterface.Pathfinder;

import java.io.*;
import java.util.*;

/**
 * This class implements a test driver that uses a script file format
 * to test an implementation of Dijkstra's algorithm on a graph.
 */
public class PathfinderTestDriver {

    private final Map<String, Graph<Double, String>> graphs = new HashMap<>();
    private final PrintWriter output;
    private final BufferedReader input;

    public static void main(String[] args) {
        // You only need a main() method if you choose to implement
        // the 'interactive' test driver, as seen with GraphTestDriver's sample
        // code. You may also delete this method entirely and just
    }

    // Leave this constructor public
    public PathfinderTestDriver(Reader r, Writer w) {
        input = new BufferedReader(r);
        output = new PrintWriter(w);
    }

    // Leave this method public
    public void runTests()
            throws IOException {
        String inputLine;
        while((inputLine = input.readLine()) != null) {
            if((inputLine.trim().length() == 0) ||
                    (inputLine.charAt(0) == '#')) {
                // echo blank and comment lines
                output.println(inputLine);
            } else {
                // separate the input line on white space
                StringTokenizer st = new StringTokenizer(inputLine);
                if(st.hasMoreTokens()) {
                    String command = st.nextToken();

                    List<String> arguments = new ArrayList<String>();
                    while (st.hasMoreTokens()) {
                        arguments.add(st.nextToken());
                    }

                    executeCommand(command, arguments);
                }
            }
            output.flush();
        }
    }

    private void executeCommand(String command, List<String> arguments) {
        try {
            switch (command) {
                case "CreateGraph":
                    createGraph(arguments);
                    break;
                case "AddNode":
                    addNode(arguments);
                    break;
                case "AddEdge":
                    addEdge(arguments);
                    break;
                case "ListNodes":
                    listNodes(arguments);
                    break;
                case "ListChildren":
                    listChildren(arguments);
                    break;
                case "FindPath":
                    findPath(arguments);
                    break;
                default:
                    output.println("Unrecognized command: " + command);
                    break;
            }
        } catch (Exception e) {
            output.println("Exception: " + e.toString());
        }
    }

    private void createGraph(List<String> arguments) {
        if(arguments.size() != 1) {
            throw new CommandException("Bad arguments to CreateGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        createGraph(graphName);
    }

    private void createGraph(String graphName) {
        graphs.put(graphName, new Graph<>());
        output.println("created graph " + graphName);
    }

    private void addNode(List<String> arguments) {
        if(arguments.size() != 2) {
            throw new CommandException("Bad arguments to AddNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1);

        addNode(graphName, nodeName);
    }

    private void addNode(String graphName, String nodeName) {
        Graph<Double, String> graph = graphs.get(graphName);
        graph.addNode(nodeName);
        output.println("added node " + nodeName + " to " + graphName);
    }

    private void addEdge(List<String> arguments) {
        if(arguments.size() != 4) {
            throw new CommandException("Bad arguments to AddEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        Double edgeLabel = Double.parseDouble(arguments.get(3));

        addEdge(graphName, parentName, childName, edgeLabel);
    }

    private void addEdge(String graphName, String parentName, String childName,
                         Double edgeLabel) {
        Graph<Double, String> graph = graphs.get(graphName);
        graph.addEdge(parentName, childName, edgeLabel);
        output.println(String.format("added edge %.3f", edgeLabel) + " from " + parentName + " to " +
                childName + " in " + graphName);
    }

    private void listNodes(List<String> arguments) {
        if(arguments.size() != 1) {
            throw new CommandException("Bad arguments to ListNodes: " + arguments);
        }
        String graphName = arguments.get(0);
        listNodes(graphName);
    }

    private void listNodes(String graphName) {
        Graph<Double, String> graph = graphs.get(graphName);
        Set<String> nodes = new TreeSet<>(graph.getNodes());
        String res = graphName + " contains:";
        for(String node : nodes) {
            res += " " + node;
        }
        output.println(res);
    }

    private void listChildren(List<String> arguments) {
        if(arguments.size() != 2) {
            throw new CommandException("Bad arguments to ListChildren: " + arguments);
        }
        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        listChildren(graphName, parentName);
    }

    private void listChildren(String graphName, String parentName) {
        Graph<Double, String> graph = graphs.get(graphName);
        List<Edge<Double, String>> edges = new ArrayList<>();
        edges.addAll(graph.getEdges(parentName));
        Collections.sort(edges, new Comparator<Graph.Edge<Double, String>>() {
            @Override
            public int compare(Edge<Double, String> edge1, Edge<Double, String> edge2) {
                if(!edge1.getDestination().equals(edge2.getDestination())) {
                    return edge1.getDestination().compareTo(edge2.getDestination());
                } else {
                    return Double.compare(edge1.getLabel(), edge2.getLabel());
                }
            }
        });
        String res = "the children of " + parentName + " in " + graphName + " are:";
        for (Edge<Double, String> edge : edges) {
            res += " " + edge.getDestination() + String.format("(%.3f", edge.getLabel());
        }
        output.println(res);
    }

    private void findPath(List<String> arguments) {
        if(arguments.size() != 3) {
            throw new CommandException("Bad arguments to findPath: " + arguments);
        }
        String graphName = arguments.get(0);
        String nodeA = arguments.get(1).replaceAll("_", " ");
        String nodeB = arguments.get(2).replaceAll("_", " ");
        findPath(graphName, nodeA, nodeB);
    }

    private void findPath(String graphName, String nodeA, String nodeB) {
        Graph<Double, String> graph = graphs.get(graphName);
        if(!graph.containNode(nodeA) && !graph.containNode(nodeB)) {
            output.println("unknown node " + nodeA);
            output.println("unknown node " + nodeB);
        } else if(!graph.containNode(nodeA)) {
            output.println("unknown node " + nodeA);
        } else if(!graph.containNode(nodeB)) {
            output.println("unknown node " + nodeB);
        } else {
            output.println("path from " + nodeA + " to " + nodeB + ":");
            Path<String> path = Pathfinder.findPath(graph, nodeA, nodeB);
            if(path == null) {
                output.println("no path found");
            } else {
                Iterator<Path<String>.Segment> itr = path.iterator();
                while(itr.hasNext()) {
                    Path<String>.Segment segment = itr.next();
                    output.println(segment.getStart() + " to " + segment.getEnd() +
                            " with weight " + String.format("%.3f", segment.getCost()));
                }
                output.println("total cost: " + String.format("%.3f", path.getCost()));
            }
        }
    }

    /**
     * This exception results when the input file cannot be parsed properly
     **/
    static class CommandException extends RuntimeException {

        public CommandException() {
            super();
        }

        public CommandException(String s) {
            super(s);
        }

        public static final long serialVersionUID = 3495;
    }
}
