package graph;

import java.util.*;

/**
 * <b>Graph</b> represents a <b>mutable</b> labeled graph. A labeled graph is a collection of
 * nodes and edges. Each edge of nodes has a label and a destination.
 * <b>E</b> is the type of edges' labels, <b>T</b> is the type of the nodes.
 * Specification fields:
 *  @spec.specfield graph : Collection
 *  @spec.specfield NumberOfEdges : int
 */
public class Graph<E, T> {

    private static final boolean DEBUG = false;

    /** The length of the square's sides. */
    private Map<T, HashSet<Edge<E, T>>> graph;
    /** The number of the edges. */
    private int NumberOfEdges;

    // Representation invariant:
    //      graph is not null and doesn't contain nodes with null values.
    //      All node destinations for edges are valid.
    //      NumberOfEdges >= 0 and will never be null.
    //      graph can be empty but will never be null.

    // Abstraction function:
    //      AF(this) = a graph where it contains the nodes as keys and edges
    //                 saved in a set as values.

    /**
     * @spec.effects Constructs an empty graph.
     */
    public Graph() {
        graph = new HashMap<>();
        NumberOfEdges = 0;
        checkRep();
    }

    /**
     * Return the number of nodes in graph.
     * @return Number of nodes in graph.
     */
    public int getNumberOfNodes() {
        return graph.size();
    }

    /**
     * Return the number of edges in graph.
     * @return Number of edges in graph.
     */
    public int getNumberOfEdges() {
        return NumberOfEdges;
    }

    /**
     * Return a set of all the nodes in graph.
     * @return A set of all the nodes in graph.
     */
    public Set<T> getNodes() {
        return new HashSet<>(graph.keySet());
    }

    /**
     *  Return <code>true</code> if graph is empty; otherwise, <code>false</code>.
     * @return <code>true</code> if graph is empty; otherwise, <code>false</code>.
     */
    public boolean isEmpty() {
        return graph.isEmpty();
    }

    /**
     * Return <code>true</code> if graph contains node <var>node</var>; otherwise,
     * return <code>false</code>.
     * @param node Node to be checked
     * @return <code>true</code> if graph contains the node; otherwise, <code>false</code>.
     * @throws IllegalArgumentException if <code>n == null</code>
     */
    public boolean containNode(T node) {
        checkRep();
        if(node == null) {
            throw new IllegalArgumentException();
        }
        return graph.containsKey(node);
    }

    /**
     * Add a node to graph and return <code>true</code> if node isn't already in the
     * graph and will be added; otherwise, return <code>false</code>
     * @param node Node to be added.
     * @spec.effects Add a node to graph if it isn't already in the graph
     * @spec.modifies this.graph
     * @return <code>true</code> if node isn't already in the graph and successfully added;
     * otherwise, <code>false</code>, if node is already in graph.
     * @throws IllegalArgumentException if <code>node == null</code>
     */
    public boolean addNode(T node) {
        checkRep();
        if(node == null) {
            throw new IllegalArgumentException();
        }
        if(!graph.containsKey(node)) {
            graph.put(node, new HashSet<>());
            checkRep();
            return true;
        }
        checkRep();
        return false;
    }

    /**
     * Add an edge from node, <var>node1</var>, to node, <var>node2</var> with label,
     * <var>label</var>. Return <code>false</code> if already exists an edge with same
     * starting node, <var>node1</var>, destination node, <var>node2</var>, and label,
     * <var>label</var>; otherwise, return <code>true</code>, a new edge will be added.
     * @param node1 Starting node of edge.
     * @param node2 Destination node of edge.
     * @param label Edge's Label to be added.
     * @spec.effects Adds a new edge from starting node, <var>node1</var>, to destination
     * node, <var>node2</var>, with label, <var>label</var>, to graph.
     * @spec.modifies this.graph
     * @return <code>true</code> if edge is successfully added; otherwise, <code>false</code>.
     * @throws IllegalArgumentException if <var>node1</var> or <var>node2</var> are not
     * valid nodes, or if <var>node1</var>, <var>node2</var>, or <var>label</var> are null
     */
    public boolean addEdge(T node1, T node2, E label) {
        checkRep();
        if(node1 == null || node2 == null || label == null || !graph.containsKey(node1) || !graph.containsKey(node2)) {
            throw new IllegalArgumentException();
        }
        boolean addedFlag = false;
        Edge<E, T> newEdge = new Edge<>(label, node2);
        if(!graph.get(node1).contains(newEdge)) {
            graph.get(node1).add(newEdge);
            addedFlag = true;
            NumberOfEdges++;
        }
        checkRep();
        return addedFlag;
    }

    /**
     * Return a set of edges of a specified node.
     * @param node Node
     * @return the set of outgoing edges of node <var>n</var>
     * @throws IllegalArgumentException if <var>node</var> is null or is already in graph
     */
    public Set<Edge<E, T>> getEdges(T node) {
        if(node == null || !graph.containsKey(node)) {
            throw new IllegalArgumentException();
        }
        return new HashSet<>(graph.get(node));
    }

    /**
     * Return a string representation of graph
     * @return a String representation of graph
     */
    public String toString() {
        checkRep();
        return graph.toString();
    }

    /**
     * Throws exceptions if RI is violated.
     */
    private void checkRep() {
        assert graph != null : "graph should never be null.";
        assert NumberOfEdges >= 0 : "NumberOfEdges should always be greater than or equal to 0.";

        if(DEBUG) {
            for(T node : graph.keySet()) {
                assert node != null : "Nodes in graph should never be null.";
                for(Edge<E, T> edge : graph.get(node)) {
                    assert edge != null : "Edges in graph should never be null.";
                    assert graph.containsKey(edge.getDestination()) : "All nodes' destinations should always be valid.";
                }
            }
        }
    }

    /**
     * <b>Edge</b> shows an <b>immutable</b> edge with a label and a destination node for a graph.
     * <b>E</b> is the type of edges' labels, <b>T</b> is the type of the destination
     * @spec.specfield label : String
     * @spec.specfield destination : String
     */
    public static class Edge<E, T> {

        // Representation invariant:
        //      label is not null
        //      destination is not null

        // Abstraction function:
        //      AF(this) = an edge in a directed graph e such that
        //                 e.label = label and e.destination = destination

        /** The node the edge is pointing from. */
        private final E label;
        /** The node the edge is pointing to. */
        private final T destination;

        /**
         *
         * @param label A label for edge
         * @param destination A destination node for edge.
         * @spec.requires label != null, destination != null
         * @spec.effects Construct an Edge with given label and destination
         * @throws IllegalArgumentException if label == null or destination == null
         */
        public Edge(E label, T destination) {
            if(label == null || destination == null) {
                throw new IllegalArgumentException();
            }
            this.label = label;
            this.destination = destination;
            checkRep();
        }

        /**
         * Return The label of edge.
         * @return Edge's label
         */
        public E getLabel() {
            return label;
        }

        /**
         * Return The destination of edge.
         * @return Edge's destination
         */
        public T getDestination() {
            return destination;
        }

        /**
         * Return A representation of this edge in a String, "destination (label)"
         * @return A string representing the edge
         */
        @Override
        public String toString() {
            checkRep();
            return destination.toString() + "(" + label.toString() + ")";
        }

        /**
         * Return true if the object, <var>edge</var> represent the same edge as this, if
         * they have the same label and destination.
         * @param edge Object to be compared
         * @return true if <var>edge</var> represents the same edge as this Edge
         */
        @Override
        public boolean equals(Object edge) {
            if(!(edge instanceof Edge<?, ?>)) {
                return false;
            }
            Edge<?, ?> e = (Edge<?, ?>) edge;
            return this.label.equals(e.label) && this.destination.equals(e.destination);
        }

        /**
         * Return A hash code of edge.
         *
         * @return A hash code of edge
         */
        @Override
        public int hashCode() {
            checkRep();
            return label.hashCode() + destination.hashCode() * 11;
        }

        /**
         * Throws exceptions if RI is violated.
         */
        private void checkRep() {
            assert this.label != null : "A graph's label should never be null.";
            assert this.destination != null : "A graph's destination should never be null";
        }

    }
}