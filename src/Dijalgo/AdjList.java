package Dijalgo;

import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by a00987765 on 3/22/2017.
 */
public class AdjList extends Controller{

    private LinkedList<Pair<Integer,Integer>>[] adjList;

    public AdjList(ArrayList points) {

        adjList = new LinkedList[points.size()];

        for (int i=0; i < adjList.length;i++) {
            adjList[i] = new LinkedList<>();
        }

    }

    public void addEdge(int start, int end, int cost) {
        adjList[start].add(new Pair<>(end,cost));
    }
    public int getNumberOfNodes() {
        return adjList.length;
    }

    public int getNumberofEdgesFromNodes(int start) {
        return adjList[start].size();
    }

    public LinkedList<Pair<Integer,Integer>> getEdgesFromNode(int start) {
        LinkedList<Pair<Integer,Integer>> edgeList = new LinkedList<>(adjList[start]);
        return edgeList;
    }

    public void printAdjList() {
        int i =0;
        taLog.setText("");
        for (LinkedList<Pair<Integer,Integer>> list : adjList) {
            taLog.appendText("adjacencyList["+i+"] -> ");
            for (Pair<Integer,Integer> edge: list) {
                taLog.appendText(edge.getKey()+"("+edge.getValue()+")");
            }
            i++;
            taLog.appendText("\n");
        }
    }

}
