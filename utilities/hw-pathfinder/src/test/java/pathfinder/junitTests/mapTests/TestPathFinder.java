package pathfinder.junitTests.mapTests;

import graph.Graph;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import pathfinder.textInterface.Pathfinder;
import static org.junit.Assert.assertEquals;

/**
 * This is a test class for PathFinder
 */
public class TestPathFinder {

    @Rule public Timeout globalTimeout = Timeout.seconds(30);

    private Graph<Double, String> map;

    @Before
    public void setUp() {
        map = new Graph<>();
        map.addNode("A");
        map.addNode("B");
        map.addNode("C");
        map.addEdge("A", "B", 1.0);
        map.addEdge("A", "C", 0.2);
        map.addEdge("C", "B", 0.6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindPathStartNotInGraph() {
        Pathfinder.findPath(map, "A", "D");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindPathDestNotInGraph() {
        Pathfinder.findPath(map, "D", "B");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindPathNodesNotInGraph() {
        Pathfinder.findPath(map, "D", "E");
    }
}
