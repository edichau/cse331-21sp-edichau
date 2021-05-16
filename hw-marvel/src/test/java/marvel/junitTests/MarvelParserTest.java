package marvel.junitTests;
import graph.Graph;
import static org.junit.Assert.*;

import marvel.MarvelParser;
import marvel.MarvelPaths;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MarvelParserTest {
    @Test(expected = IOException.class)
    public void createMarvelGraphTest() throws IOException {
        try {
            MarvelParser.parseData("test", new ArrayList<>(), new HashMap<>());
        } catch (IOException e) {
            throw new IOException();
        }
    }
}
