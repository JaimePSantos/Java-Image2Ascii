package com.company;
import javax.swing.*;

public class GUI {
        public void run(){
            JFrame frame = new JFrame("My First GUI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900,600);
            JButton button = new JButton("Press");
            frame.getContentPane().add(button); // Adds Button to content pane of frame
            frame.setVisible(true);
    }
}
