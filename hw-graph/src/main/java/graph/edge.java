package graph;

/** edge represents an edge.
 *
 *  Specification fields:
 *  *   @spec.specfield node end : String node // destination of an edge
 *  *   @spec.specfield node start : String node // start of an edge
 *      @spec.specfield label : String // label of the edge
 */

public class edge {
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
