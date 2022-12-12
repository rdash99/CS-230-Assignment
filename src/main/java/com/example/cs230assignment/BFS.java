package com.example.cs230assignment;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This class represents the BFS algorithm
 *
 * @author Thomas McAuley
 * @version 1.0
 */
public class BFS {
    private Vertex startVertex;

    /**
     * This is the constructor for the BFS class
     *
     * @param startVertex the vertex to start the BFS from
     */
    public BFS(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    /**
     * This method will traverse the graph using the BFS algorithm
     *
     * @return Vertex
     */
    public Vertex traverse() {
        int distance = 0;
        Vertex nextInteractable = null;
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(startVertex);
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            if (current != null && !current.isVisited()) {
                current.setVisited(true);
                distance++;
                current.setDistanceFromSmartThief(distance);
                queue.addAll(current.getNeighbours());
                if (nextInteractable == null || current
                        .getDistanceFromSmartThief() < nextInteractable
                                .getDistanceFromSmartThief()) {
                    nextInteractable = current;
                }
            }
        }
        return nextInteractable;
    }
}
