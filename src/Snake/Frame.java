package Snake;

import Snake.grafika.*;
import Snake.jednostki.*;
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Frame extends JFrame {
    public Frame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gra Snake");
        setResizable(false);
        init();
    }

    public void init(){
        setLayout(new GridLayout(1,1,0,0));

        Screen screen = new Screen();
        add(screen);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
