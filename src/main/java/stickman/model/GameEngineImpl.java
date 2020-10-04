package stickman.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameEngineImpl implements GameEngine{

    private Level level;

    private ConfigurationLevel configurationLevel;

    public GameEngineImpl(String filename) {
        configurationLevel=new ConfigurationLevel(filename);
        level=configurationLevel.getLevel();
        }

    @Override
    public Level getCurrentLevel() {
        return level;
    }

    @Override
    public void startLevel() {

    }

    @Override
    public boolean jump() {
        return level.jump();
    }

    @Override
    public boolean moveLeft() {
        return level.moveLeft();
    }

    @Override
    public boolean moveRight() {
        return level.moveRight();
    }

    @Override
    public boolean stopMoving() {
        return level.stopMoving();
    }

    @Override
    public void tick() {
    }


}
