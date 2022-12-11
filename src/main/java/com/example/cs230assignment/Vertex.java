package com.example.cs230assignment;

import java.util.LinkedList;
import java.util.List;

/**
 * This class is used to create a vertex.
 * 
 * @author Thomas McAuley
 * @version 1.0
 */
public class Vertex<T> {
    private Entity vertexData;
    private boolean visited;
    private List<Vertex> neighbours = new LinkedList<>();
    private int distanceFromSmartThief = 0;

    public Vertex(Entity vertexData) {
        this.vertexData = vertexData;
    }

    /**
     * @return boolean
     */
    public boolean isVisited() {
        return this.visited;
    }

    /**
     * @param visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * @param neighbours
     */
    public void setNeighbours(List<Vertex> neighbours) {
        this.neighbours = neighbours;
    }

    /**
     * @return List<Vertex>
     */
    public List<Vertex> getNeighbours() {
        return this.neighbours;
    }

    /**
     * @param distanceFromSmartThief
     */
    public void setDistanceFromSmartThief(int distanceFromSmartThief) {
        this.distanceFromSmartThief = distanceFromSmartThief;
    }

    /**
     * @return int
     */
    public int getDistanceFromSmartThief() {
        return this.distanceFromSmartThief;
    }

    /**
     * @return Entity
     */
    public Entity getVertexData() {
        return this.vertexData;
    }
}
