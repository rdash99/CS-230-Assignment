package com.example.cs230assignment;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    private Vertex startVertex;

    public BFS(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public Vertex traverse() {
        int distance = 0;
        Vertex nextInteractable = null;
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(startVertex);
        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            if (!current.isVisited()) {
                current.setVisited(true);
                distance ++;
                current.setDistanceFromSmartThief(distance);
                queue.addAll(current.getNeighbours());
                if (nextInteractable == null || current.getDistanceFromSmartThief() < nextInteractable.getDistanceFromSmartThief()) {
                    nextInteractable = current;
                }
            }
        }
        return nextInteractable;
    }
}
