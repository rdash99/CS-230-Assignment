package com.example.cs230assignment;

public class Entity extends DrawShape {
    private String entityName;
    protected int[] coord;

    public void Entity(String entityNamePass, int[] coordPass) {
        this.coord = coordPass;
        this.entityName = entityNamePass;
    }

    public String getEntityName() {
        return this.entityName;
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub

    }

}
