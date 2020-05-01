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

            System.out.println("Enter a command:");
            System.out.print(">");
            cmd = this.scan.nextLine().toLowerCase();
            if(cmd.equals("stop".toLowerCase())){
                break;
            }else if(cmd.equals("new image")){
                this.start();
            }else if(cmd.equals("ascii".toLowerCase())|cmd.equals("ascii".toLowerCase())){
                img.printAsci(img.getBrightness());
                continue;
            }
            else if(cmd.equals("resize")){
                this.resize(img,img.getWidth()-100,img.getHeight()-100);
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

    public void resize(ImageParser img, Integer imgWidth, Integer imgHeight) throws IOException {
        if(imgWidth>0&&imgHeight>0){
            BufferedImage resizedImage = img.resizeImage(imgWidth,imgHeight);
            img.imgOut("merotest1","jpg",true);
        }

    }
}
