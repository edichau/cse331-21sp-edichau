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
    public static final boolean DEBUG = true;

    public static class node {
        // name of the node
        String name;
        /**
         * Creates a node with a name.
         *
         * @param name of the node
         * @spec.effects Creates a node with a name.
         */
        public node(String name) {
            this.name = name;
            checkRep();
        }

        /**
         * Returns name of node
         *
         * @return returns name of the node
         */
        public String getName() {
            checkRep();
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof node))
                return false;
            node n = (node) o;
            if (n.getName().equals(this.name)) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode(){
            return name.hashCode();
        }

        public void checkRep() {
            assert (name != null);
        }
    }

    public static class edge {
        // first node
        node n1;
        //second node
        node n2;
        //label of edge
        String label;
        /**
         * Creates an edge that connects the 2 nodes with a label.
         *
         * @param n1    first node of the edge
         * @param n2    last node of the edge
         * @param label label for the edge
         * @spec.requires n1 != null and n2 != null and label != null
         * @spec.effects Creates an edge that connects the 2 nodes with a label.
         */
        public edge(node n1, node n2, String label) {
            this.n1 = n1;
            this.n2 = n2;
            this.label = label;
            checkRep();
        }

        /**
         * Gets starting node of the edge
         *
         * @return the starting node
         */
        public node getStart() {
            checkRep();
            return n1;
        }

        /**
         * Gets end node of the edge
         *
         * @return the ending node
         */
        public node getEnd() {
            checkRep();
            return n2;
        }

        /**
         * Gets label of the edge
         *
         * @return the label of the edge
         */
        public String getLabel() {
            checkRep();
            return label;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof edge))
                return false;
            edge e = (edge) o;
            if (e.getStart().equals(this.n1) && e.getEnd().equals(this.n2) && e.getLabel().equals(this.label)) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode(){
            return label.hashCode()*n1.hashCode()*n2.hashCode();
        }




        private void checkRep(){
            assert (n1 != null);
            assert (n2 != null);
            assert (label != null);
        }
    }

    // graph data structure
    HashMap<node, ArrayList<edge>> graph = new HashMap<>();

        /**
         * Creates an empty graph with no nodes or edges.
         *
         * @spec.effects constructs an empty graph with no nodes or edges
         */
        public Graph() {
            graph = new HashMap<>();
            checkRep();
        }

        /**
         * Inserts a node into the graph
         *
         * @param n node being inserted
         * @spec.requires n != null
         * @spec.effects inserts a node into the graph
         * @spec.modifies the current graph instance
         */
        public void insertNode(node n) {
            checkRep();
            graph.put(n, null);
            checkRep();
        }

        /**
         * Inserts an edge into the graph
         *
         * @param e edge being inserted
         * @spec.requires e != null
         * @spec.effects inserts an edge into the graph
         * @spec.modifies the current graph instance
         */
        public void insertEdge(edge e) {
            checkRep();
            ArrayList<edge> edges = graph.get(e.getStart());
            edges.add(e);
            graph.put(e.getStart(), edges);
            checkRep();
        }

        /**
         * Removes a node from the graph
         *
         * @param n node being removed
         * @spec.requires n != null
         * @spec.effects removes a node from the graph
         * @spec.modifies the current graph instance
         */
        public void removeNode(node n) {
            checkRep();
            graph.remove(n);
            checkRep();
        }

        /**
         * Removes an edge from the graph
         *
         * @param e edge being removed
         * @spec.requires e != null
         * @spec.effects removes an edge from the graph
         * @spec.modifies the current graph instance
         */
        public void removeEdge(edge e) {
            checkRep();
            ArrayList<edge> edges = graph.get(e.getStart());
            edges.remove(e);
            graph.put(e.getStart(), edges);
            checkRep();
        }

        /**
         * list all nodes in the graph
         *
         * @return list of nodes in the graph
         */
        public ArrayList<node> listNodes() {
            checkRep();
            ArrayList<node> nodes = new ArrayList<>();
            for (node key : graph.keySet()) {
                nodes.add(key);
            }
            checkRep();
            return nodes;
        }

        /**
         * list all edges in the graph
         *
         * @return list of edges in the graph
         */
        public ArrayList<edge> listEdges() {
            checkRep();
            ArrayList<edge> ret = new ArrayList<>();
            for (ArrayList<edge> edges: graph.values()) {
                for (edge e : edges){
                    ret.add(e);
                }
            }
            checkRep();
            return ret;
        }

        /**
         * list all children of a node
         *
         * @param n node being removed
         * @return a map of edges of a nodes children
         * @spec.requires n != null
         */
        public HashMap<node, ArrayList<edge>> listChildren(node n) {
            checkRep();
            HashMap<node, ArrayList<edge>> ret = new HashMap<>();
            ArrayList<edge> edges = graph.get(n);
            for(edge e : edges){
                ArrayList<edge> edges2 = new ArrayList<>();
                edges2.add(e);
                ret.put(e.getStart(), edges);
            }
            checkRep();
            return ret;
        }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Graph))
            return false;
        Graph g = (Graph) o;
        if (g.listNodes().equals(this.listNodes()) && g.listEdges().equals(this.listEdges())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
            int hashcode = 0;
            for (node n : graph.keySet()){
                hashcode += n.hashCode();
            }
        return hashcode;
    }

        private void checkRep(){
            assert (graph != null);
            if (DEBUG){
                for (HashMap.Entry<node, ArrayList<edge>> entry : graph.entrySet()) {
                    node n = entry.getKey();
                    ArrayList<edge> edges = entry.getValue();
                    assert (n != null);
                    assert (edges != null);
                    for (edge e : edges){
                        assert (e.getStart().equals(n));
                    }
                }
            }
        }
    }
