package com.example.cs230assignment;

import java.util.LinkedList;
import java.util.List;

public class Vertex<T> {
    private Entity vertexData;
    private boolean visited;
    private List<Vertex> neighbours = new LinkedList<>();
    private int distanceFromSmartThief = 0;

    public Vertex(Entity vertexData) {
        this.vertexData = vertexData;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setNeighbours(List<Vertex> neighbours) {
        this.neighbours = neighbours;
    }

    public List<Vertex> getNeighbours() {
        return this.neighbours;
    }

    public void setDistanceFromSmartThief(int distanceFromSmartThief) {
        this.distanceFromSmartThief = distanceFromSmartThief;
    }

    public int getDistanceFromSmartThief() {
        return this.distanceFromSmartThief;
    }

    public Entity getVertexData() {
        return this.vertexData;
    }
}
