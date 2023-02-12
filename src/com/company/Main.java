package com.company;

import java.io.IOException;
import java.util.Scanner;

import com.company.UserInterface;


public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

//        Scanner scanner = new Scanner(System.in);
//        UserInterface ui = new UserInterface(scanner);
//        ui.start();
        GUI gui = new GUI();
        gui.run();
    }

}

