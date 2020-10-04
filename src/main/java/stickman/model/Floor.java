package stickman.model;

public class Floor implements Entity{
    private String imagePath;
    private double xPos;
    private double yPos;
    private double height;
    private double width;
    private Layer layer;


    public Floor(String imagePath, double xPos, double yPos, double height, double width, Layer layer) {
        this.imagePath = imagePath;
        this.xPos = xPos;
        this.yPos = yPos;
        this.height = height;
        this.width = width;
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
}
