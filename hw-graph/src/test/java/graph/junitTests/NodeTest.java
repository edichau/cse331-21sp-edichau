package graph.junitTests;

import graph.Graph.node;
import org.junit.*;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for Node ADT implementation
 */
public class NodeTest {

    //Makes nodes easier to work with
    public static node node(String name){
        return new node(name);
    }

    //Test for if getName method gets the name of the node correctly
    @Test
    public void getNameTest() {
        assertEquals("getName() not getting name", "test", node("test").getName());
    }
}
