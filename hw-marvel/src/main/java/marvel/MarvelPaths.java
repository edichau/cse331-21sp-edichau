package marvel;
import graph.Graph;

import java.util.*;

public class MarvelPaths {

    /**
     * Builds graph using the data from the file.
     *
     * @param filename file to be used to build the graph
     * @throws Exception                if fail to read data from the specified
     *                                  file or the format of the file does not match the
     *                                  expected format
     * @throws IllegalArgumentException if filename == null
     * @requires filename != null
     */
    public static Graph buildGraph(String filename) throws Exception {
        if (filename == null)
            throw new IllegalArgumentException("filename cannot be null.");
        Graph marvelGraph = new Graph();
        ArrayList<String> chars = new ArrayList<>();
        HashMap<String, ArrayList<String>> books = new HashMap<>();
        MarvelParser.parseData(filename, chars, books);

        // add characters as nodes to the graph
        for (String character : chars) {
            marvelGraph.insertNode(new Graph.node(character));
        }

        // connect characters as nodes with books as labels to the edge
        for (String book : books.keySet()) {
            ArrayList<String> charsInBook = books.get(book);
            int i = 1;
            for (String c1 : charsInBook) {
                System.out.println("first char " + c1);
                List<String> charsSublist = charsInBook.subList(i, charsInBook.size());
                for (String c2 : charsSublist) {
                    System.out.println("second char " + c2);
                    if (!(c1.equals(c2))) {
                        marvelGraph.insertEdge(new Graph.node(c1), new Graph.node(c2), book);
                        marvelGraph.insertEdge(new Graph.node(c2), new Graph.node(c1), book);
                    }
                }
                i++;
            }
        }
        return marvelGraph;
    }

    /**
     * Finds the shortest path from one character to another character.
     *
     * @param graph the graph used to find shortest path from start to end
     * @param start a character
     * @param end   another character
     * @return the shortest path from start to end, or null if
     * no path exists from start to end
     * @throws IllegalArgumentException if either start or end is
     *                                  not in the graph
     * @requires graph != null && start != null && end != null
     */
    public static ArrayList<Graph.edge> BFS(Graph graph, String start, String end) {
        if (graph == null)
            throw new IllegalArgumentException("graph cannot be null.");

        if (start == null || end == null)
            throw new IllegalArgumentException("start and end cannot be null.");

        // nodes to be visited
        LinkedList<String> worklist = new LinkedList<>();

        // Each key in paths is a visited node and each value is
        // a path from start to that node.
        HashMap<String, ArrayList<Graph.edge>> paths = new HashMap<>();

        paths.put(start, new ArrayList<>());
        // start is a key of paths since the above code put it in paths

        //@SuppressWarnings("keyfor")
        String start2 = start;
        worklist.add(start2);

        while (!(worklist.isEmpty())) {
            String character = worklist.removeFirst();
            if (character.equals(end)) {
                ArrayList<Graph.edge> path = paths.get(character);
                return new ArrayList<>(path);
            }

            // use special comparator to get edge in alphabetical order
            // comparator compare the alphabetical order of destination of edge first,
            // then compare the alphabetical order of label of edge
            Set<Graph.edge> edges = new TreeSet<Graph.edge>(new Comparator<Graph.edge>() {
                @Override
                public int compare(Graph.edge o1, Graph.edge o2) {
                    if (!(o1.getEnd().equals(o2.getEnd())))
                        return o1.getEnd().compareTo(o2.getEnd());

                    if (!(o1.getLabel().equals(o2.getLabel())))
                        return o1.getLabel().compareTo(o2.getLabel());

                    return 0;
                }
            });

            HashMap<Graph.node, ArrayList<Graph.edge>> children = graph.listChildren(new Graph.node(character));
            for (ArrayList<Graph.edge> edgeList : children.values()) {
                edges.addAll(edgeList);
            }

            for (Graph.edge edge : edges) {
                String dest = edge.getEnd().getName();

                if (!(paths.containsKey(dest))) {
                    // if the node is not already visited, then map the path
                    // to this node by appending edge from character to this node
                    // to path from start to character
                    ArrayList<Graph.edge> path = paths.get(character);
                    ArrayList<Graph.edge> path_post = new ArrayList<>(path);
                    path_post.add(edge);
                    paths.put(dest, path_post);
                    // dest is a key of paths since the above code put it in paths
                    //@SuppressWarnings("keyfor")
                    /*@KeyFor("paths")*/
                    String dest2 = dest;
                    worklist.add(dest2);  // mark this node as visited
                }
            }
        }

        // no path exists from start to end
        return null;
    }

    /**
     * Allows user to type in two characters and find the
     * shortest path of those two characters.
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Graph mgraph = MarvelPaths.buildGraph("marvel.csv");
        System.out.println("Find the shortest path for two Marvel characters.");
        Scanner reader = new Scanner(System.in);
        String start, end;
        System.out.print("Please type in a character: ");
        start = reader.nextLine();
        System.out.print("Please type in another character: ");
        end = reader.nextLine();

        String currentNode = start;
        String result = "path from " + start + " to " + end + ":";
        ArrayList<Graph.edge> path = MarvelPaths.BFS(mgraph, start, end);

        if (path == null) {
            result += "\n" + "no path found";
        } else {
            for (Graph.edge edge : path) {
                result += "\n" + currentNode + " to " + edge.getStart() + " via " + edge.getLabel();
                currentNode = edge.getEnd().getName();
            }
        }
        System.out.println(result);
        reader.close();
    }
}

