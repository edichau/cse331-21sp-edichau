package graph.junitTests;

import graph.node;
import graph.edge;
import org.junit.*;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for Edge ADT implementation
 */
public class EdgeTest {
    public node n1 = new node("n1");
    public node n2 = new node("n2");
    //makes nodes easier to work with
    public static node node(String name){
        return new node(name);
    }

    //Makes edges easier to work with
    public static edge edge(node n1, node n2, String label){
        return new edge(n1, n2, "test");
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
