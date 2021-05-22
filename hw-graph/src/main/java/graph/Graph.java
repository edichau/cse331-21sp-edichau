package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** Graph represents a mutable directed labeled graph.
 *
 *  Specification fields:
 *  *  @spec.specfield node : String node //nodes of a the graph
 *  *  @spec.specfield edge: point edge //edges that connect the nodes of a graph
 */

public class Graph<T extends Comparable<T>, E extends Comparable<E>> {
    public static final boolean DEBUG = false;

    public static class node<T extends Comparable<T>> {
        // name of the node
        T name;
        /**
         * Creates a node with a name.
         *
         * @param name of the node
         * @spec.effects Creates a node with a name.
         */
        public node(T name) {
            this.name = name;
            checkRep();
        }

        /**
         * Returns name of node
         *
         * @return returns name of the node
         */
        public T getName() {
            checkRep();
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof node<?>))
                return false;
            node<?> n = (node<?>) o;
            return n.getName().equals(this.name);
        }

        @Override
        public int hashCode(){
            return name.hashCode();
        }

        public void checkRep() {
            assert (name != null);
        }

        public int compareTo(node<T> n) {
            if (!(name.equals(n.getName()))) {
                checkRep();
                return name.compareTo(n.getName());
            }
            checkRep();
            return 0;
        }
    }

    public static class edge<T extends Comparable<T>, E> {
        // first node
        node<T> n1;
        //second node
        node<T> n2;
        //label of edge
        E label;
        /**
         * Creates an edge that connects the 2 nodes with a label.
         *
         * @param n1    first node of the edge
         * @param n2    last node of the edge
         * @param label label for the edge
         * @spec.requires n1 != null and n2 != null and label != null
         * @spec.effects Creates an edge that connects the 2 nodes with a label.
         */
        public edge(node<T> n1, node<T> n2, E label) {
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
        public node<T> getStart() {
            checkRep();
            return n1;
        }

        /**
         * Gets end node of the edge
         *
         * @return the ending node
         */
        public node<T> getEnd() {
            checkRep();
            return n2;
        }

        /**
         * Gets label of the edge
         *
         * @return the label of the edge
         */
        public E getLabel() {
            checkRep();
            return label;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof edge<?,?>))
                return false;
            edge<?, ?> e = (edge<?, ?>) o;
            return e.getStart().equals(this.n1) && e.getEnd().equals(this.n2) && e.getLabel().equals(this.label);
        }

        @Override
        public int hashCode(){
            return label.hashCode()*n1.hashCode()*n2.hashCode();
        }

//        /**
//         * Compares this object with the specified object for order. Returns
//         * a negative integer, zero, or a positive integer as this object
//         * is less than, equal to, or greater than the specified object.
//         *
//         * @param e object to be compared
//         * @return a negative integer, zero, or a positive integer as
//         * this object is less than, equal to, or greater than
//         * the specified object
//         */
//        @Override
//        public int compareTo(edge e) {
//            checkRep();
//
//            // compare label first
//            if (!(label.equals(e.label))) {
//                checkRep();
//                return label.compareTo(e.label);
//            }
//
//            // if label is the same as this, compare destination
//            // using their hashcode
//            if (!(n2.equals(e.getEnd()))) {
//                checkRep();
//                return this.getEnd().hashCode() - e.getEnd().hashCode();
//            }
//
//            checkRep();
//            return 0;
//        }




        private void checkRep(){
            assert (n1 != null);
            assert (n2 != null);
            assert (label != null);
        }
    }

    // graph data structure
    HashMap<node<T>, ArrayList<edge<T, E>>> graph = new HashMap<>();

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
        public void insertNode(node<T> n) {
            checkRep();
            if(!graph.containsKey(n)) {
                if(n == null){
                    throw new IllegalArgumentException();
                }
                this.graph.put(n, new ArrayList<>());
            }
            checkRep();
        }

        /**
         * Inserts an edge into the graph
         *
         * @param parent the start of the edge
         * @param child the end of the edge
         * @param label the name of the edge
         * @spec.requires edge != null
         * @spec.effects inserts an edge into the graph
         * @spec.modifies the current graph instance
         */
        public void insertEdge(node<T> parent, node<T> child, E label) {
            checkRep();
            if (!graph.containsKey(parent) || !graph.containsKey(child)){
                throw new IllegalArgumentException();
            }
            edge<T, E> newEdge = new edge<>(parent, child, label);
            graph.get(parent).add(newEdge);
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
        public void removeNode(node<E> n) {
            checkRep();
            if (n == null)
                throw new IllegalArgumentException();
            graph.remove(n);
            checkRep();
        }

        /**
         * Removes an edge from the graph
         *
         * @param parent the start of the edge
         * @param child the end of the edge
         * @param label the name of the edge
         * @spec.requires e != null
         * @spec.effects removes an edge from the graph
         * @spec.modifies the current graph instance
         */
        public void removeEdge(node<E> parent, node<E> child, T label) {
            checkRep();
            if (parent == null || child == null || label == null)
                throw new IllegalArgumentException();
            if (!graph.containsKey(parent) || !graph.containsKey(child)){
                throw new IllegalArgumentException();
            }
            edge<E, T> newEdge = new edge<>(parent, child, label);
            graph.get(parent).remove(newEdge);
            checkRep();
        }

        /**
         * list all nodes in the graph
         *
         * @return list of nodes in the graph
         */
        public ArrayList<node<T>> listNodes() {
            checkRep();
            ArrayList<node<T>> nodes = new ArrayList<>(graph.keySet());
            checkRep();
            return nodes;
        }

        /**
         * list all edges in the graph
         *
         * @return list of edges in the graph
         */
        public ArrayList<edge<T, E>> listEdges() {
            checkRep();
            ArrayList<edge<T, E>> ret = new ArrayList<>();
            for (ArrayList<edge<T, E>> edges : graph.values()) {
                ret.addAll(edges);
            }
            checkRep();
            return ret;
        }

        /**
         * list all children of a node
         *
         * @param parent node being removed
         * @return a map of edges of a nodes children
         * @spec.requires n != null
         */
        public HashMap<node<T>, ArrayList<edge<T, E>>> listChildren(node<T> parent) {
            checkRep();
            if(parent == null)
                throw new IllegalArgumentException();
            HashMap<node<T>, ArrayList<edge<T, E>>> children = new HashMap<>();
            if (graph.containsKey(parent)) {
                ArrayList<edge<T, E>> edges = graph.get(parent);
                for(edge<T, E> e : edges){
                    if(children.containsKey(e.getStart())){
                        children.get(e.getStart()).add(e);
                    } else {
                        ArrayList<edge<T, E>> edges2 = new ArrayList<>();
                        edges2.add(e);
                        children.put(e.getStart(), edges2);
                    }
                }
            }
            checkRep();
            return children;
        }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Graph<?, ?>))
            return false;
        Graph<?, ?> g = (Graph<?, ?>) o;
        if (g.listNodes().equals(this.listNodes()) && g.listEdges().equals(this.listEdges())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
            int hashcode = 0;
            for (node<T> n : graph.keySet()){
                hashcode += n.hashCode();
            }
        return hashcode;
    }

        private void checkRep(){
            assert (graph != null);
            if (DEBUG){
                for (Map.Entry<node<T>, ArrayList<edge<T, E>>> entry : graph.entrySet()) {
                    node<T> n = entry.getKey();
                    ArrayList<edge<T, E>> edges = entry.getValue();
                    assert (n != null);
                    assert (edges != null);
                    for (edge<T, E> e : edges){
                        assert (e.getStart().equals(n));
                    }
                }
            }
        }
    }
