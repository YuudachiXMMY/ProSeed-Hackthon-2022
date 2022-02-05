package pathfinder.junitTests.mapTests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import pathfinder.CampusMap;

/**
 * This is a test class for CampusMap
 */
public class TestCampusMap {

    @Rule public Timeout globalTimeout = Timeout.seconds(30);

    @Test
    public void testMapConstructor() {
        new CampusMap("campus_buildings.tsv", "campus_paths.tsv");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapConstructWithNullBuildingFile() {
        new CampusMap(null, "campus_paths.tsv");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapConstructWithNullPathFile() {
        new CampusMap("campus_buildings.tsv", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapConstructWithNullArguments() {
        new CampusMap(null, null);
    }
}
