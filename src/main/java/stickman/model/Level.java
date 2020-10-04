package stickman.model;

import java.util.List;

public interface Level {
    List<Entity> getEntities();
    double getHeight();
    double getWidth();

    void tick();

    double getFloorHeight();
    double getHeroX();

    boolean jump();//tell hero what to do
    boolean moveLeft();
    boolean moveRight();
    boolean stopMoving();
    //new
    void addEntity(Entity entity);
}
