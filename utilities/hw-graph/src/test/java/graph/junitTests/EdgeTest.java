package graph.junitTests;

import org.junit.*;
import static org.junit.Assert.*;
import graph.Graph.Edge;
import org.junit.rules.Timeout;

/**
 * This class contains test cases for testing the implementation of Edge inner class.
 */
public class EdgeTest {

    @Rule public Timeout globalTimeout = Timeout.seconds(20);

    private static final String NODE1 = "node1";
    private static final String EDGE1 = "edge1";
    private static final String EDGE2 = "edge2";

    private Edge<String, String> edge1;
    private Edge<String, String> edge2;
    private Edge<String, String> edge3;

    @Before
    public void setUpEdgeTests() throws Exception {
        edge1 = new Edge<String, String>(EDGE1, NODE1);
        edge2 = new Edge<String, String>(EDGE1, NODE1);
        edge3 = new Edge<String, String>(EDGE2, NODE1);
    }

    @Test
    public void testConstructing1() {
        new Edge<String, String>(EDGE1, NODE1);
    }

    @Test
    public void testConstructing2() {
        new Edge<String, String>(EDGE2, NODE1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructingNullLabel() {
        new Edge<String, String>(null, NODE1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructingNullDest1() {
        new Edge<String, String>(EDGE1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructingNullDest2() {
        new Edge<String, String>(EDGE2, null);
    }

    @Test
    public void testGetDestination() {
        assertEquals(NODE1, edge1.getDestination());
    }

    @Test
    public void testGetLabel1() {
        assertEquals(EDGE1, edge1.getLabel());
    }

    @Test
    public void testGetLabel2() {
        assertEquals(EDGE1, edge2.getLabel());
    }

    @Test
    public void testGetLabel3() {
        assertEquals(EDGE2, edge3.getLabel());
    }

    @Test
    public void testToString() {
        assertEquals("node1(edge1)", edge1.toString());
    }

    @Test
    public void testEqualsSameEdges() {
        assertTrue(edge1.equals(edge2));
    }

    @Test
    public void testEqualsDifferentEdges() {
        assertFalse(edge1.equals(edge3));
    }

    @Test
    public void testHashCodeSameEdges() {
        assertEquals(edge1.hashCode(), edge2.hashCode());
    }

    @Test
    public void testHashCodeDifferentEdges() {
        assertNotEquals(edge1.hashCode(), edge3.hashCode());
    }
}
