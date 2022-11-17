package com.example.cs230assignment;
public class Entity extends DrawShape{
    private String entityName;
    protected Int[Null,Null] coord;

    public void Entity(String entityNamePass, Int[] coordPass){
        this.coord = coordPass;
        this.entityName = entityNamePass;
    }

    public String getEntityName(){
        return this.entityName;
    }

}
