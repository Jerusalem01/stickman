package stickman.model;

public class Hero implements Entity{
    private String imagePath;
    private String size;
    private double xPos;
    private double yPos;
    private double height;
    private double width;
    private Layer layer;

    public Hero(String imagePath, double xPos, double yPos, String size, Layer layer) {
        this.imagePath = imagePath;
        if(size=="normal"){
            this.height = 24;
            this.width = 20;
        }else if(size=="large"){
            this.height = 50;
            this.width = 40;
        }
        this.xPos = xPos;
        this.yPos = yPos;

        this.layer = layer;
    }



    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public double getXPos() {
        return xPos;
    }

    @Override
    public double getYPos() {
        return yPos;
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
    public Layer getLayer() {
        return layer;
    }
    public void move(){}


}
