package stickman.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationLevel {
    private double cloudV;
    private String heroSize;
    private double hero_start_x;
    private Level level;
    public ConfigurationLevel(String json){
        List<Entity> entities=new ArrayList<>();
        level=new LevelImpl(entities,100,200,250);
        JSONParser jsonParser=new JSONParser();
        try{
            JSONObject jsonObject=(JSONObject) jsonParser.parse(new FileReader(json));

            heroSize=(String)jsonObject.get("stickmanSize");
            hero_start_x=(double) ((JSONObject) jsonObject.get("stickmanPos")).get("x");

            cloudV=(double)jsonObject.get("cloudVelocity");

            //}
        }catch ( ParseException | IOException e){
            e.printStackTrace();
        }
    }
    public Level getLevel(){
        level.addEntity(new Hero("ch_stand1.png",10,215,heroSize, Entity.Layer.FOREGROUND));
        level.addEntity(new Cloud("cloud_2.png",60,80,150,80, Entity.Layer.FOREGROUND,cloudV));
        level.addEntity(new Floor("landscape_0000_1_trees.png",0,400,100,640, Entity.Layer.FOREGROUND));
        level.addEntity(new Floor("slimeBa.png",200,220,30,60, Entity.Layer.FOREGROUND));
        return level;
    }


}
