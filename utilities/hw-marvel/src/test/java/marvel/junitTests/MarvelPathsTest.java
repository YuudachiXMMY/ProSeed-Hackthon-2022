package marvel.junitTests;

import org.junit.*;
import graph.Graph;
import marvel.MarvelPaths;
import org.junit.rules.Timeout;

/**
 * This class contains a set of test cases that can be used to test the implementation of the
 * MarvelPaths class.
 *
 */

public class MarvelPathsTest {

    @Rule public Timeout globalTimeout = Timeout.seconds(30);

    private Graph<String, String> graph1;

    @Before
    public void setUpMarvelTests() {
        graph1 = MarvelPaths.createGraph("staffSuperheroes.tsv");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindPathStartNotInGraph() {
        MarvelPaths.findPath(graph1, "CSE-Staffs", "CSE331");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindPathDestNotInGraph() {
        MarvelPaths.findPath(graph1, "Ernst-the-Bicycling-Wizard", "CSE999");
    }
}
