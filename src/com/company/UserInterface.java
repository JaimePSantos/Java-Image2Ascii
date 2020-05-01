package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Scanner;

public class UserInterface {
    private Scanner scan;

    public UserInterface(Scanner scanner){
        this.scan = scanner;
    }

    public void start() throws IOException {
        System.out.println("Picture Name:");
        String firstPic = this.scan.nextLine();
        ImageParser img = new ImageParser(firstPic);
        this.availableCmds();
        String cmd ="";

        while(true){
            System.out.println("");
            System.out.println("Enter a command:");
            System.out.print(">");
            cmd = this.scan.nextLine().toLowerCase();
            if(cmd.equals("stop".toLowerCase())){
                break;
            }else if(cmd.equals("help".toLowerCase())){
              this.availableCmds();
              continue;
            }else if(cmd.equals("new image")){
                this.start();
            }else if(cmd.equals("ascii".toLowerCase())|cmd.equals("ascii".toLowerCase())){
                img.printAsci(img.getBrightness());
                continue;
            }else if(cmd.equals("resize")){
                System.out.println("New width?");
                int newWidth = Integer.valueOf(this.scan.nextLine());
                System.out.println("New height?");
                int newHeight = Integer.valueOf(this.scan.nextLine());
                System.out.println("Name for resized image:");
                String imageName = this.scan.nextLine();
                this.resize(img,newWidth,newHeight,imageName);
                continue;
            }else if(cmd.equals("size".toLowerCase())){
                System.out.println(img.getWidth()+"x"+img.getHeight());
                continue;
            }
            else{
                System.out.println("Invalid command.");
                continue;
            }

        }
    }

    public void availableCmds(){
        System.out.println("");
        System.out.println("Commands:");
        System.out.println("help: lists available commands.");
        System.out.println("stop: stops the program.");
        System.out.println("new image: loads a new image.");
        System.out.println("ascii: converts the image into ASCII-art and prints it.");
        System.out.println("size: prints the size of the loaded image.");
        System.out.println("resize: resizes the image to new width and height.");

    }

    public void resize(ImageParser img, Integer imgWidth, Integer imgHeight,String imageName) throws IOException {
        if(imgWidth>0&&imgHeight>0){
            img.resizeImage(imageName,imgWidth,imgHeight);
        }

    }
}
