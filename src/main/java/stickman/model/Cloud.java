package stickman.model;

public class Cloud implements Entity{
    private String imagePath;
    private double xPos;
    private double yPos;
    private double height;
    private double width;
    private Layer layer;
    private double cloudV;

    public Cloud(String imagePath, double xPos, double yPos, double height, double width, Layer layer,double cloudV) {
        this.imagePath = imagePath;
        this.xPos = xPos;
        this.yPos = yPos;
        this.height = height;
        this.width = width;
        this.layer = layer;
        this.cloudV=cloudV;

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

    public void move(){
        xPos-=cloudV;
    }
}
