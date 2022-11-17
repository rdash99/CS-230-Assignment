public class Entity extends DrawShape{
    private String entityName;
    protected Int[Null,Null] coord;

    public void Entity(entityNamePass, coordPass){
        this.coord = coordPass;
        this.entityName = entityNamePass;
    }

    public String getEntityName(){
        return this.entityName;
    }

}