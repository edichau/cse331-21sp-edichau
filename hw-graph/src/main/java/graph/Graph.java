package graph;

import java.util.ArrayList;

/** Graph represents a mutable directed labeled graph.
 *
 *  Specification fields:
 *  *  @spec.specfield node : String node //nodes of a the graph
 *  *  @spec.specfield edge: point edge //edges that connect the nodes of a graph
 */

public class Graph {

    /**
     * Creates an empty graph with no nodes or edges.
     *
     * @spec.effects constructs an empty graph with no nodes or edges
     */
    public Graph(){

    }
    /**
     * Inserts a node into the graph
     * @param n node being inserted
     * @spec.requires n != null
     * @spec.effects inserts a node into the graph
     * @spec.modifies the current graph instance
     */
    public void insertNode(node n) {

    }
    /**
     * Inserts an edge into the graph
     * @param e edge being inserted
     * @spec.requires e != null
     * @spec.effects inserts an edge into the graph
     * @spec.modifies the current graph instance
     */
    public void insertEdge(edge e){

    }
    /**
     * Removes a node from the graph
     * @param n node being removed
     * @spec.requires n != null
     * @spec.effects removes a node from the graph
     * @spec.modifies the current graph instance
     */
    public void removeNode(node n) {

    }
    /**
     * Removes an edge from the graph
     * @param e edge being removed
     * @spec.requires e != null
     * @spec.effects removes an edge from the graph
     * @spec.modifies the current graph instance
     */
    public void removeEdge(edge e){

    }
    /**
     * list all nodes in the graph
     * @return list of nodes in the graph
     */
    public ArrayList<node> listNodes(){
        throw new RuntimeException("not yet implemented");
    }

    /**
     * list all edges in the graph
     * @return list of edges in the graph
     */
    public ArrayList<edge> listEdges(){
        throw new RuntimeException("not yet implemented");
    }
    /**
     * list all children of a node
     * @param n node being removed
     * @spec.requires n != null
     * @return list of children of a node
     */
    public ArrayList<node> listChildren(node n){
        throw new RuntimeException("not yet implemented");
    }
    /**
     * finds the shortest path between two nodes
     * @param n1 start node
     * @param n2 end node
     * @spec.requires n1 != null and n2 != null
     * @return list containing the shortest path
     */
    public ArrayList<edge> findShortestPath(node n1, node n2){
        throw new RuntimeException("not yet implemented");
    }


}
