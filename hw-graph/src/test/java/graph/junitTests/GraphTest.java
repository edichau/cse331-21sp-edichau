package graph.junitTests;

import graph.Graph;
import graph.Graph.node;
import graph.Graph.edge;

import org.junit.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for Graph ADT implementation
 */
public class GraphTest {

    public node n1 = new node("n1");
    public node n2 = new node("n2");
    public edge e1 = new edge(n1, n2, "e1");
    public edge e11 = new edge(n1, n2, "n1 to n2 again");
    public edge e2 = new edge(n2, n1, "n2 to n1");
    public edge e3 = new edge(n1, n1, "n1 to n1");
    public edge e4 = new edge(n2, n2, "n2 to n2");
    public ArrayList<node> nodes = new ArrayList<>();
    public ArrayList<edge> edges = new ArrayList<>();
    public Graph testGraph = new Graph();

    //makes nodes easier to work with
    public static node node(String name){
        return new node(name);
    }

    //Makes edges easier to work with
    public static edge edge(node n1, node n2, String label){
        return new edge(n1, n2, "test");
    }

    @After
    public void clear(){
        testGraph = new Graph();
        nodes.clear();
        edges.clear();
    }
    //Test for if insertNodes method inserts a node into the graph correctly
    @Test
    public void insertNodeTest() {
        nodes.add(n1);
        testGraph.insertNode(n1);
        assertEquals("insertNode() not inserting node properly", nodes, testGraph.listNodes());

        nodes.add(n2);
        testGraph.insertNode(n2);
        assertEquals("insertNode() not inserting 2nd node properly", nodes, testGraph.listNodes());
    }
    //Test for if insertNodes method throws an exception at null
    @Test(expected = IllegalArgumentException.class)
    public void insertNullNodeTest() {
        testGraph.insertNode(null);
    }

    //Test for if removeNodes method throws an exception at null
    @Test(expected = IllegalArgumentException.class)
    public void removeNullNodeTest() {
        testGraph.removeNode(null);
    }

    //Test for if insertEdge method throws an exception at null
    @Test(expected = IllegalArgumentException.class)
    public void insertNullEdgeTest() {
        testGraph.insertEdge(null, null, null);
    }

    //Test if removeNodes removes nodes properly
    @Test
    public void removeNodeTest(){
        testGraph.insertNode(n1);
        nodes.add(n1);
        nodes.remove(n1);
        testGraph.removeNode(n1);
        assertEquals("removeNode() not removing node properly", nodes, testGraph.listNodes());
    }

    //Test if removeNodes removes nothing properly
    @Test
    public void removeNodeNotInGraphTest(){
        testGraph.insertNode(n1);
        nodes.add(n1);
        nodes.remove(n2);
        testGraph.removeNode(n2);
        assertEquals("removeNode() not removing node properly", nodes, testGraph.listNodes());
    }

    //Test for if removeEdge method throws an exception at null
    @Test(expected = IllegalArgumentException.class)
    public void removeNullEdgeTest() {
        testGraph.removeNode(null);
    }

    //Test for if removeEdge method properly inserts edge
    @Test
    public void removeEdgeTest() {
        testGraph.insertNode(n1);
        testGraph.insertNode(n2);
        testGraph.insertEdge(n1, n2, "e1");
        edges.add(e1);
        testGraph.removeEdge(n1, n2, "e1");
        edges.remove(e1);
        assertEquals("removeEdge() not removing edges properly", edges, testGraph.listEdges());
    }

    //Test for if listChildren method throws at null children
    @Test(expected = IllegalArgumentException.class)
    public void listChildrenNullTest() {
        testGraph.listChildren(null);
    }

    //Test for if listChildren method gets the children nodes of a node correctly
    @Test
    public void listChildrenTest() {
        testGraph.insertNode(n1);
        testGraph.insertNode(n2);
        testGraph.insertEdge(n1, n2, "e1");
        HashMap<node, ArrayList<edge>> test = new HashMap<>();
        edges.add(e1);
        test.put(n1, edges);
        assertEquals("listChildren() not listing children properly", test, testGraph.listChildren(n1));
    }

    //Test for if listChildren method gets the children nodes of a node when there are no children correctly
    @Test
    public void listChildrenNoneTest() {
        testGraph.insertNode(n1);
        HashMap<node, ArrayList<edge>> test = new HashMap<>();
        assertEquals("listChildren() not listing children properly", test, testGraph.listChildren(n1));
    }
}
