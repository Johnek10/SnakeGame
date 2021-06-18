package Snake.jednostki;

import java.awt.*;

public class Apple {
    private int xCoor, yCoor, width, height;

    /**
     *
     * @param xCoor wspolrzedna x
     * @param yCoor wspolrzedna y
     * @param tileSize rozmiar
     */
    public Apple(int xCoor, int yCoor, int tileSize){
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        width = tileSize;
        height = tileSize;
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillRect(xCoor*width,yCoor*height,width,height);
    }

    public int getxCoor() {
        return xCoor;
    }

    public void setxCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    public int getyCoor() {
        return yCoor;
    }

    public void setyCoor(int yCoor) {
        this.yCoor = yCoor;
    }

}
