package graph;

import java.util.ArrayList;
import java.util.HashMap;

/** Graph represents a mutable directed labeled graph.
 *
 *  Specification fields:
 *  *  @spec.specfield node : String node //nodes of a the graph
 *  *  @spec.specfield edge: point edge //edges that connect the nodes of a graph
 */

public class Graph {
    HashMap<node, ArrayList<edge>> graph = new HashMap<>();

    public static class node {
        String name;
        /**
         * Creates a node with a name.
         * @param name of the node
         * @spec.effects Creates a node with a name.
         */
        public node(String name){
            this.name = name;
        }

        /**
         * Returns name of node
         *
         * @return returns name of the node
         */
        public String getName(){
            return name;
        }

        @Override
        public boolean equals(node n){
            if (n.getName().equals(this.name)){
                return true;
            } else {
                return false;
            }
        }
    }
    public static class edge {
        /**
         * Creates an edge that connects the 2 nodes with a label.
         * @param n1 first node of the edge
         * @param n2 last node of the edge
         * @param label label for the edge
         * @spec.requires n1 != null and n2 != null and label != null
         * @spec.effects Creates an edge that connects the 2 nodes with a label.
         */
        public edge (node n1, node n2, String label){

        }
        /**
         * Gets starting node of the edge
         * @return the starting node
         */
        public node getStart(){
            throw new RuntimeException("not yet implemented");
        }
        /**
         * Gets end node of the edge
         * @return the ending node
         */
        public node getEnd(){
            throw new RuntimeException("not yet implemented");
        }
        /**
         * Gets label of the edge
         * @return the label of the edge
         */
        public String getLabel(){
            throw new RuntimeException("not yet implemented");
        }
    }
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
