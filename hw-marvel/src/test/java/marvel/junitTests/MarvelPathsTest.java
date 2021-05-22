package marvel.junitTests;
import graph.Graph;
import static org.junit.Assert.*;

import marvel.MarvelPaths;
import org.junit.Test;

public class MarvelPathsTest {

    @Test(expected = IllegalArgumentException.class)
    // Test if filename is bad, if it will run
    public void MarvelGraphTest() throws Exception {
        assertNotEquals(new Graph<String, String>(), MarvelPaths.buildGraph("bad file name"));
    }
}
