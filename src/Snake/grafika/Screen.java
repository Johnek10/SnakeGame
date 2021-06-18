package Snake.grafika;
import Snake.jednostki.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Screen extends JPanel implements Runnable {

    private static final int WIDTH = 600, HEIGHT = 600;
    private Thread thread;
    private boolean running = false;
    private BodyPart b;
    private ArrayList<BodyPart> snake;

    private Apple apple;
    private ArrayList<Apple> apples;

    private int xCoor = 10, yCoor = 10;
    private int size = 5;
    private int ticks = 0;
    private Key key;

    private Random randomNumber;

    private boolean right = true, left = false, up = false, down = false;

    public Screen() {
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        key = new Key();
        addKeyListener(key);
        randomNumber = new Random();
        snake = new ArrayList<BodyPart>(); //dlugosc
        apples = new ArrayList<Apple>();
        start();
    }

    /**/
    public void tick() {
        if (snake.size() == 0) {
            b = new BodyPart(xCoor, yCoor, 20);
            snake.add(b);
        }

        /*losowanie pierwszego jablka*/
        if (apples.size() == 0){
            /*losownanie wspolrzednych dla pierwszego jablka*/
            int xCoor = randomNumber.nextInt(30);
            int yCoor = randomNumber.nextInt(30);
            apple = new Apple(xCoor,yCoor,20); //utworzenie nowego jablka
            apples.add(apple); //dodanie do kolekcji
        }

        /*usuwanie zjedzonego jablka*/
        for(int i = 0; i < apples.size(); i++){
            if(xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()){
                size++; //zwiekszanie weza
                apples.remove(i);
                System.out.println("Punkty: "+(size-5));
                i--;
            }
        }
        for(int i = 0; i < snake.size();i++){
            if(xCoor == snake.get(i).getxCoordinate() && yCoor == snake.get(i).getyCoor()){
                if(i != snake.size()-1){
                    stop(); //kolizja
                }
            }
        }
        ticks++;

        /*zapamietywanie ostatnigo kierunku */
        if (ticks > 2000000) {
            if (right) xCoor++;
            if (left) xCoor--;
            if (up) yCoor--;
            if (down) yCoor++;
            ticks = 0;

            b = new BodyPart(xCoor, yCoor, 20);
            snake.add(b);

            if (snake.size() > size) {
                snake.remove(0);//usuwanie z tylu weza
            }
        }
    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);

        for (int i = 0; i < WIDTH / 20; i++) {
            g.drawLine(i * 20, 0, i * 20, HEIGHT);
        }
        for (int i = 0; i < HEIGHT / 20; i++) {
            g.drawLine(0, i * 20, WIDTH, i * 20);
        }

        for (int i = 0; i < snake.size(); i++) {
            snake.get(i).draw(g);
        }
        for(int i = 0; i < apples.size(); i++){
            apples.get(i).draw(g);
        }
    }

    public void start() {
        running = true;
        thread = new Thread(this, "Game loop");
        thread.start(); //uruchomienie nowego watku
    }

    public void stop() {
        running = false;
        try{
            thread.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (running) {
            tick();
            repaint();
        }
    }

    private class Key implements KeyListener{
        @Override
        public void keyTyped(KeyEvent keyEvent) {
        }
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            /*right arrow, left arrow, up, down*/
            if(key == KeyEvent.VK_RIGHT && !left){
                up = false;
                down = false;
                right = true;
            }
            if(key == KeyEvent.VK_LEFT && !right){
                up = false;
                down = false;
                left = true;
            }
            if(key == KeyEvent.VK_UP && !down){
                left = false;
                right = false;
                up = true;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                left = false;
                right = false;
                down = true;
            }
        }
        @Override
        public void keyReleased(KeyEvent keyEvent) {
        }
    }
}