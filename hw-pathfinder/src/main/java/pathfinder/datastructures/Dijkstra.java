package pathfinder.datastructures;

import graph.Graph;

import java.util.*;

public class Dijkstra<T extends Comparable<T>>{
    public Path<Graph.node<T>> Dij(Graph<T, Double> graph, T start, T dest) {
        // Dijkstra's algorithm assumes a graph with nonnegative edge weights.

        Graph.node<T> startNode = new Graph.node<>(start);
        Graph.node<T> destNode = new Graph.node<>(dest);
        Queue<Path<Graph.node<T>>> active = new PriorityQueue<>(Comparator.comparingDouble(Path::getCost));
        // Each element is a path from start to a given node.
        // A path's “priority” in the queue is the total cost of that path.
        // Nodes for which no path is known yet are not in the queue.
        Set<Graph.node<T>> finished = new HashSet<>();

        // Initially we only know of the path from start to itself, which has
        // a cost of zero because it contains no edges.
        active.add(new Path<>(startNode));

        while (!active.isEmpty()){
            // minPath is the lowest-cost path in active and,
            // if minDest isn't already 'finished,' is the
            // minimum-cost path to the node minDest
            Path<Graph.node<T>> minPath = active.remove();
            Graph.node<T> minDest = minPath.getEnd();

            if (minDest.equals(destNode)){
                return minPath;
            }

            if (finished.contains(minDest)){
                continue;
            }

            Collection<ArrayList<Graph.edge<T, Double>>> childEdges = graph.listChildren(minDest).values();
            for (ArrayList<Graph.edge<T, Double>> edges : childEdges){ // For all children of minDest
                for (Graph.edge<T, Double> edge : edges) {
                    // If we don't know the minimum-cost path from start to child,
                    // examine the path we've just found
                    if (!finished.contains(edge.getEnd())){
                        Path<Graph.node<T>> newPath = minPath.extend(edge.getEnd(), edge.getLabel());
                        active.add(newPath);
                    }
                }
            }
            finished.add(minDest);
        // If the loop terminates, then no path exists from start to dest.
        // The implementation should indicate this to the client.
        }
        throw new IllegalArgumentException("no path");
    }
}
