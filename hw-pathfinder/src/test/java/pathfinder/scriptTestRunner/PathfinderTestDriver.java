/*
 * Copyright (C) 2021 Kevin Zatloukal.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2021 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package pathfinder.scriptTestRunner;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import graph.Graph;
import graph.Graph.node;
import graph.Graph.edge;

import java.io.*;
import java.util.*;

import org.junit.Rule;
import org.junit.rules.Timeout;
import pathfinder.datastructures.Dijkstra;
import pathfinder.datastructures.Path;
import pathfinder.datastructures.Point;

/**
 * This class implements a testing driver which reads test scripts from
 * files for testing Graph, the Marvel parser, and your BFS algorithm.
 */
public class PathfinderTestDriver {

    @Rule public Timeout globalTimeout = Timeout.seconds(10);

    // ***************************
    // ***  JUnit Test Driver  ***
    // ***************************

    /**
     * String -> Graph: maps the names of graphs to the actual graph
     **/
    // TODO for the student: Uncomment and parameterize the next line correctly:
    private final Map<String, Graph<String, Double>> graphs = new HashMap<String, Graph<String, Double>>();
    private final PrintWriter output;
    private final BufferedReader input;

    /**
     * @spec.requires r != null && w != null
     * @spec.effects Creates a new GraphTestDriver which reads command from
     * {@code r} and writes results to {@code w}
     **/
    // Leave this constructor public
    public PathfinderTestDriver(Reader r, Writer w) {
        input = new BufferedReader(r);
        output = new PrintWriter(w);
    }

    /**
     * @throws IOException if the input or output sources encounter an IOException
     * @spec.effects Executes the commands read from the input and writes results to the output
     **/
    // Leave this method public
    public void runTests() throws IOException {
        String inputLine;
        while((inputLine = input.readLine()) != null) {
            if((inputLine.trim().length() == 0) ||
                    (inputLine.charAt(0) == '#')) {
                // echo blank and comment lines
                output.println(inputLine);
            } else {
                // separate the input line on white space
                StringTokenizer st = new StringTokenizer(inputLine);
                if(st.hasMoreTokens()) {
                    String command = st.nextToken();

                    List<String> arguments = new ArrayList<>();
                    while(st.hasMoreTokens()) {
                        arguments.add(st.nextToken());
                    }

                    executeCommand(command, arguments);
                }
            }
            output.flush();
        }
    }

    private void executeCommand(String command, List<String> arguments) {
        try {
            switch(command) {
                case "CreateGraph":
                    createGraph(arguments);
                    break;
                case "AddNode":
                    addNode(arguments);
                    break;
                case "AddEdge":
                    addEdge(arguments);
                    break;
                case "ListNodes":
                    listNodes(arguments);
                    break;
                case "ListChildren":
                    listChildren(arguments);
                    break;
                case "FindPath":
                    Dijkstra(arguments);
                    break;
                default:
                    output.println("Unrecognized command: " + command);
                    break;
            }
        } catch(Exception e) {
            String formattedCommand = command;
            formattedCommand += arguments.stream().reduce("", (a, b) -> a + " " + b);
            output.println("Exception while running command: " + formattedCommand);
            e.printStackTrace(output);
        }
    }

    private void createGraph(List<String> arguments) {
        if(arguments.size() != 1) {
            throw new CommandException("Bad arguments to CreateGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        createGraph(graphName);
    }

    private void createGraph(String graphName) {
        Graph<String, Double> graph = new Graph<>();

        graphs.put(graphName, graph);
        output.println("created the graph " + graphName);
    }

    private void Dijkstra(List<String> arguments) {
        if(arguments.size() != 3) {
            throw new CommandException("Bad arguments to FindPath: " + arguments);
        }

        Graph<String, Double> graph = graphs.get(arguments.get(0));
        String start = arguments.get(1);
        String end = arguments.get(0);
        Dijkstra(graph, start, end);
    }

    private void Dijkstra(Graph<String, Double> graph, String start, String end) {
        Dijkstra<String> dij = new Dijkstra<>();
        Path<node<String>> path = dij.Dij(graph, start, end);
        double totalCost = 0;
        output.println("path from " + start + " to " + end + ":");
        assert path != null;
        for (Path<node<String>>.Segment edge : path){
            output.println(edge.getStart() + " to " + edge.getEnd() + " with weight " + String.format("%.3f", edge.getCost()));
            totalCost += edge.getCost();
        }
        output.println("total cost: " + String.format("%.3f", totalCost));
    }

    private void addNode(List<String> arguments) {
        if(arguments.size() != 2) {
            throw new CommandException("Bad arguments to AddNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1);

        addNode(graphName, nodeName);
    }

    private void addNode(String graphName, String nodeName) {
        Graph<String, Double> graph = graphs.get(graphName);
        node<String> node = new node<>(nodeName);
        graph.insertNode(node);
        output.println("added node " + nodeName +  " to " + graphName);
    }

    private void addEdge(List<String> arguments) {
        if(arguments.size() != 4) {
            throw new CommandException("Bad arguments to AddEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        String edgeLabel = arguments.get(3);
        double dnum = Double.parseDouble(edgeLabel);

        addEdge(graphName, parentName, childName, dnum);
    }

    private void addEdge(String graphName, String parentName, String childName,
                         Double edgeLabel) {
        // TODO Insert your code here.
        Graph<String, Double> graph = graphs.get(graphName);
        node<String> n1 = new node<>(parentName);
        node<String> n2 = new node<>(childName);
        edge<String, Double> edge = new edge<>(n1, n2, edgeLabel);
        graph.insertEdge(n1, n2, edgeLabel);
        output.println("added edge " + String.format("%.3f", edgeLabel) + " from " + parentName + " to " + childName + " in " + graphName);
    }

    private void listNodes(List<String> arguments) {
        if(arguments.size() != 1) {
            throw new CommandException("Bad arguments to ListNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        listNodes(graphName);
    }

    private void listNodes(String graphName) {
        // TODO Insert your code here.

        Graph<String, Double> graph = graphs.get(graphName);
        ArrayList<node<String>> nodes = graph.listNodes();
        String ret = "";
        for (node<String> n: nodes) {
            ret = ret + n.getName() + " ";
        }
        output.println("Graph contains: " + ret);
    }

    private void listChildren(List<String> arguments) {
        if(arguments.size() != 2) {
            throw new CommandException("Bad arguments to ListChildren: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        listChildren(graphName, parentName);
    }

    private void listChildren(String graphName, String parentName) {
        // TODO Insert your code here.

        Graph<String, Double> graph = graphs.get(graphName);
        String ret = "";
        node<String> node = new node<>(parentName);
        HashMap<Graph.node<String>, ArrayList<edge<String, Double>>> children = graph.listChildren(node);
        for (node<String> n: children.keySet()) {
            ret = ret + n.getName() + " ";
        }
        output.println("the children of " + parentName + " in " + graphName + " are: " + ret);
    }

    /**
     * This exception results when the input file cannot be parsed properly
     **/
    static class CommandException extends RuntimeException {

        public CommandException() {
            super();
        }

        public CommandException(String s) {
            super(s);
        }

        public static final long serialVersionUID = 3495;
    }
}
