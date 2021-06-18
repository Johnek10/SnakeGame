package Snake.jednostki;

import java.awt.*;

public class BodyPart {
    private int xCoordinate;
    private int yCor, width, height;

    public BodyPart(int xCoor,int yCoor,int tileSize){
        this.xCoordinate = xCoor;
        this.yCor = yCoor;
        this.width = tileSize;
        this.height = tileSize;

    }

    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(xCoordinate * width, yCor *height, width, height);
        g.setColor(Color.GREEN);
        g.fillRect(xCoordinate * width+2, yCor *height+2, width-4, height-4);
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoor() {
        return yCor;
    }

    public void setyCoor(int yCoor) {
        this.yCor = yCoor;
    }
}
