package graph.junitTests;

import graph.Graph.node;
import graph.Graph.edge;
import org.junit.*;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for Edge ADT implementation
 */
public class EdgeTest {
    public node<String> n1 = new node<>("n1");
    public node<String> n2 = new node<>("n2");
    //makes nodes easier to work with
    public static node<String> node(String name){
        return new node<String>(name);
    }

    //Makes edges easier to work with
    public static edge<String, String> edge(node<String> n1, node<String> n2, String label){
        return new edge<String, String>(n1, n2, "test");
    }

    //Test for if getLabel method gets the label of the edge correctly
    @Test
    public void getLabelTest() {
        assertEquals("getLabel() not getting label", "test", edge(n1, n2, "test").getLabel());
    }

    //Test for if getStart method gets the start node of the edge correctly
    @Test
    public void getStartTest(){
        assertEquals("getStart() not getting start node", n1, edge(n1, n2, "test").getStart());
    }

    //Test for if getEnd method gets the end node of the edge correctly
    @Test
    public void getEndTest(){
        assertEquals("getEnd() not getting end node", n2, edge(n1, n2, "test").getEnd());
    }
}
