package graph.junitTests;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import graph.Graph;
import graph.Graph.Edge;
import org.junit.rules.Timeout;

/**
 * This class contains test cases for testing the implementation of Graph class.
 */
public class GraphTest {

    @Rule public Timeout globalTimeout = Timeout.seconds(20);

    private static final String NODE1 = "node1";
    private static final String NODE2 = "node2";
    private static final String EDGE1 = "edge1";
    private static final String EDGE2 = "edge2";

    private Graph<String, String> graph;
    private Set<String> nodes;
    private Set<Edge> edges;

    @Before
    public void setUpGraphTests() throws Exception {
        graph = new Graph<>();
        nodes = new HashSet<>();
        edges = new HashSet<>();
        graph.addNode(NODE1);
        graph.addNode(NODE2);
        nodes.add(NODE1);
        nodes.add(NODE2);
        graph.addEdge(NODE1, NODE2, EDGE1);
        edges.add(new Edge<String, String>(EDGE1, NODE2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullNode() {
        graph.addNode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetEdgesWithNoNode() {
        graph.getEdges("n3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEdgeNull1() {
        graph.addEdge(null, "n2", "e2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEdgeNull2() {
        graph.addEdge("n1", null, "e2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEdgeNull3() {
        graph.addEdge("n1","n2", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEdgeNull14() {
        graph.addEdge(null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEdgeWithNodeNotInGraph1() {
        graph.addEdge("n4", NODE2, "e2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEdgeWithNodeNotInGraph2() {
        graph.addEdge(NODE1, "n4", "e2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsNullNode() {
        graph.containNode(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetEdgesWithNullNode() {
        graph.getEdges(null);
    }

    @Test
    public void testGetNodes() {
        assertEquals(nodes, graph.getNodes());
    }

    @Test
    public void testNumberOfNodes() {
        assertEquals(2, graph.getNumberOfNodes());
    }

    @Test
    public void testNumberOfEdges() {
        assertEquals(1, graph.getNumberOfEdges());
    }

    @Test
    public void testGetEdges() {
        assertEquals(edges, graph.getEdges(NODE1));
    }

    @Test
    public void testContainNodeInGraph() {
        assertTrue(graph.containNode(NODE1));
    }

    @Test
    public void testContainNodeNotInGraph() {
        assertFalse(graph.containNode("node3"));
    }

    @Test
    public void testIsEmpty() {
        assertFalse(graph.isEmpty());
    }

    @Test
    public void testToString() {
        assertEquals("{node2=[], node1=[node2(edge1)]}", graph.toString());
    }

    @Test
    public void testAddNewEdgeInGraph() {
        assertEquals(false, graph.addEdge(NODE1, NODE2, EDGE1));
    }

    @Test
    public void testAddNewEdge() {
        assertEquals(true, graph.addEdge(NODE2, NODE1, EDGE2 ));
    }
}
