package com.company;

import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private Scanner scan;
    private ImageParser img;

    public UserInterface(Scanner scanner){
        this.scan = scanner;
        this.img = null;
    }

    public void start() throws IOException {
        System.out.println("Picture Name:");
        String firstPic = this.scan.nextLine();
        String getPath = UserInterface.class.getResource(firstPic).toString();
        System.out.println(getPath);
        this.img = new ImageParser(getPath);
        this.availableCmds();
        String cmd ="";

        while(true){

            System.out.println("Enter a command:");
            System.out.print(">");
            cmd = this.scan.nextLine().toLowerCase();
            if(cmd.equals("stop".toLowerCase())){
                break;
            }else if(cmd.equals("new image")){
                this.start();
            }else if(cmd.equals("ascii".toLowerCase())|cmd.equals("ascii".toLowerCase())){
                this.img.printAsci(this.img.getBrightness());
                continue;
            }
            else{
                System.out.println("Invalid command.");
                continue;
            }

        }
    }

    public void availableCmds(){
        System.out.println("Commands:");
        System.out.println("stop: stops the program.");
        System.out.println("new image: loads a new image.");
        System.out.println("new file: prompts the program to ask for a new file.");
        System.out.println("ascii: converts the image into ASCII-art and prints it.");

    }
}
