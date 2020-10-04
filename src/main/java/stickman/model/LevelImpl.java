package stickman.model;

import java.util.ArrayList;
import java.util.List;

public class LevelImpl implements  Level{
    private List<Entity> entities= new ArrayList<>();
    private double height;
    private double width;
    private double floorHeight;



    public LevelImpl(List<Entity> entities,double height,double width,double floorHeight){
        this.entities=entities;
        this.height=height;
        this.width=width;
        this.floorHeight=floorHeight;
    }

    @Override
    public List<Entity> getEntities() {
        return entities;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public void tick() {

    }

    @Override
    public double getFloorHeight() {
        return floorHeight;
    }

    @Override
    public double getHeroX() {
        return 0;
    }

    @Override
    public boolean jump() {
        return false;
    }

    @Override
    public boolean moveLeft() {
        return false;
    }

    @Override
    public boolean moveRight() {
        return false;
    }

    @Override
    public boolean stopMoving() {
        return false;
    }

    @Override
    public void addEntity(Entity entity) {
        entities.add(entity);
    }
}
